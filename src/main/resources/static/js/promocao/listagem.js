document.addEventListener("DOMContentLoaded",async function(){
    popularTabela()
})

async function popularTabela(){
    $("#ativo").empty();
    $("#interr").empty();
    const res = await axios.get("/promocao");
    res.data.forEach(promocao => {
		if(promocao.status==0){
	        var dt = new Date(promocao.dataCadastro);
	        var data = dt.getDate()+"/"+(dt.getMonth()+1)+"/"+dt.getFullYear();
	        var elemento = "<tr><td>"+promocao.id+"</td><td>"+promocao.nome+"</td><td>"+data+"</td>";
	        elemento += "<td><a href='/administrativo/promocao?id="+promocao.id+"' class='btn btn-info btn-sm editar' role='button'><span class='bx bx-edit' title='Editar' aria-hidden='true'></span></a></td>";
	        elemento += "<td><a class='btn btn-sm btn-danger' data-toggle='modal' data-target='#modal-warning' onclick='abreModal("+promocao.id+")'><span class='bx bxs-x-circle' title='Remover' aria-hidden='true'></span></a></td>";
	        elemento += "<td><a class='btn btn-info btn-sm info' onclick='abreDetalhe("+promocao.id+")'><span class='bx bxs-detail' title='info' aria-hidden='true'></span></a></td>";
	        elemento += "<td><a class='btn btn-pause btn-sm pause' onclick='interrPromo("+promocao.id+")'><span class='bx bx-pause-circle' title='Interromper' aria-hidden='true'></span></a></td></tr>";
            $("#ativo").append(elemento);
		}else{
			var dt = new Date(promocao.dataCadastro);
	        var data = dt.getDate()+"/"+(dt.getMonth()+1)+"/"+dt.getFullYear();
			var elemento = "<tr><td>"+promocao.id+"</td><td>"+promocao.nome+"</td><td>"+data+"</td>";
	        elemento += "<td colspan='3'><a class='btn btn-info btn-sm editar' data-toggle='modal' data-target='#modal-warning'>";
            elemento += "<span class='bx bx-play-circle' title='Ativar' onclick='ativar("+JSON.stringify(promocao)+")' aria-hidden='true'></span></a></td></tr>";
            $("#interr").append(elemento);
		}
    });
}

//function to change table
function changeTable() {
    var op = document.getElementById("op").value;
    if (op == "0") {
        document.getElementById("ativo").style.display = "contents";
        document.getElementById("interr").style.display = "none";
    } else {
        document.getElementById("interr").style.display = "contents";
        document.getElementById("ativo").style.display = "none";
    }
}

async function abreDetalhe(id){
    $("#detailContent").empty()
    $("#detailModal").modal("toggle")
    const res = await axios.get("/itensPromocao/promocao/"+id);
    if(res.data.length>0){
        var content = '<table class="table table-striped table-hover table-sm"><thead><tr>'
        content += '<th>#</th><th>Nome do Produto</th><th>Categoria</th><th>Valor</th></tr></thead><tbody>'
        res.data.forEach(item=>{
            content += '<tr><td>'+item.id+'</td><td>'+item.nome+'</td><td>'+item.categoria+'</td><td>'+item.valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+'</td></tr>'
        })
        content += '</tbody></table>'
        $("#detailContent").append(content)
    }else{
        $("#detailContent").append("<p>Nenhum produto participante</p>")
    }
}

function abreModal(id){
    $(`#myModal`).attr('promocao',id)
    $("#myModal").modal("toggle")
}

async function interrPromo(promocao){
    await axios.delete("/promocao/"+promocao)
    .then(()=>{
        popularTabela()
    })
    .catch(err=>{
        console.error(err)
    })
}

async function ativar(promocao){
    promocao.status = 0;
    await axios.post("/promocao",promocao)
    .then(()=>{
        popularTabela()
    })
    .catch(err=>{
        console.error(err)
    })
}

async function excluirPromocao(){
    const id = $(`#myModal`).attr('promocao');
    await axios.delete("/itensPromocao/"+id)
    .then(async ()=>{
        await axios.delete("/promocao/"+id)
        .then(res=>{
            $(`#myModal`).modal(`hide`)
            popularTabela()
        })
        .catch(err=>{
            console.error(err);
        })
    })
    .catch(err=>{
        console.error(err);
    })
}
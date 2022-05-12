document.addEventListener("DOMContentLoaded",async function(){
    popularTabela()
})

async function popularTabela(){
    $("#disp").empty();
    $("#arqui").empty();
    const res = await axios.get("/promocao");
    res.data.forEach(promocao => {
        var dt = new Date(promocao.dataCadastro);
        var data = dt.getDate()+"/"+(dt.getMonth()+1)+"/"+dt.getFullYear();
        var elemento = "<tr><td>"+promocao.id+"</td><td>"+promocao.nome+"</td><td>"+data+"</td>";
        elemento += "<td><a href='/administrativo/promocao?id="+promocao.id+"' class='btn btn-info btn-sm editar' role='button'><span class='bx bx-edit' title='Editar' aria-hidden='true'></span></a></td>";
        elemento += "<td><a class='btn btn-sm btn-danger' data-toggle='modal' data-target='#modal-warning' onclick='abreModal("+promocao.id+")'><span class='bx bxs-x-circle' title='Remover' aria-hidden='true'></span></a></td>";
        elemento += "<td><a class='btn btn-info btn-sm editar' onclick='abreDetalhe("+promocao.id+")'><span class='bx bxs-detail' title='info' aria-hidden='true'></span></a></td></tr>";
        $("#disp").append(elemento);
    });
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
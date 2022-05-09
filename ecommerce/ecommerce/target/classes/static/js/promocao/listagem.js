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
        elemento += "<td><a class='btn btn-sm btn-danger' data-toggle='modal' data-target='#modal-warning' onclick='abreModal("+promocao.id+")'><span class='bx bxs-x-circle' title='Remover' aria-hidden='true'></span></a></td></tr>";
        $("#disp").append(elemento);
    });
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
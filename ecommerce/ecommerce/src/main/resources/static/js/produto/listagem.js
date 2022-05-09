document.addEventListener("DOMContentLoaded",async function(){
    popularTabela()
})

async function popularTabela(){
    $("#disp").empty();
    $("#arqui").empty();
    const res = await axios.get("/produto");
    res.data.forEach(produto => {
        if(produto.status==0){
            var elemento = "<tr><td>"+produto.id+"</td><td>"+produto.nome+"</td><td>"+produto.categoria+"</td><td>"+produto.valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</td>";
            elemento += "<td colspan='2'><a class='btn btn-info btn-sm' role='button' onclick='adicionarSacola("+JSON.stringify(produto)+")'><span class='bx bxs-shopping-bag' title='Adicionar na Sacola' aria-hidden='true'></span><td>";
            elemento += "<a class='btn btn-sm promo' role='button'><span class='bx bxs-badge-dollar' title='Promoção' aria-hidden='true'></span></a>";
            elemento += "<a href='/administrativo/produto?id="+produto.id+"' class='btn btn-info btn-sm editar' role='button'><span class='bx bx-edit' title='Editar' aria-hidden='true'></span></a>";
            elemento += "<a class='btn btn-sm btn-danger' data-toggle='modal' data-target='#modal-warning' onclick='abreModal("+produto.id+")'><span class='bx bxs-x-circle' title='Remover' aria-hidden='true'></span></a></td></tr>";
            $("#disp").append(elemento);
        }else{
            var elemento="<tr><td>"+produto.id+"</td><td>"+produto.nome+"</td><td>"+produto.categoria+"</td><td>"+produto.valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</td>"
            elemento += "<td colspan='3'><a class='btn btn-info btn-sm editar' data-toggle='modal' data-target='#modal-warning'>";
            elemento += "<span class='bx bx-upload' title='desarquivar' onclick='desarquivar("+JSON.stringify(produto)+")' aria-hidden='true'></span></a></td></tr>";
            $("#arqui").append(elemento);
        }
    });
}

function abreModal(id){
    $(`#myModal`).attr('produto',id)
    $(`#myModal`).modal(`toggle`)
}

async function desarquivar(produto){
    produto.status = 0;
    await axios.post("/produto",produto)
    .then(()=>{
        popularTabela()
    })
    .catch(err=>{
        console.error(err)
    })
}

async function excluirProduto(){
    const id = $(`#myModal`).attr('produto')
    await axios.delete("/produto/"+id)
    .then(res=>{
        $(`#myModal`).modal(`hide`)
        popularTabela();
    })
    .catch(err=>{
        console.error(err);
    })
}

function adicionarSacola(produto){
    if(localStorage.getItem('sacola')){
        sacola = JSON.parse(localStorage.getItem('sacola'));
        if(sacola.length>0){
            for(let i=0;i<sacola.length;i++){
                if(verificarSacola(produto.id)){
                    sacola[i].quantidade += 1
                }else{
                    produto.quantidade = 1;
                    sacola.push(produto);
                }
            }
        }else{
            produto.quantidade = 1;
            sacola = [produto];
        }
        localStorage.setItem('sacola',JSON.stringify(sacola))
    }else{
        produto.quantidade = 1;
        sacola = [produto];
        localStorage.setItem('sacola',JSON.stringify(sacola))
    }
    window.location.href="/administrativo/sacola"
}

function verificarSacola(id){
    sacola = JSON.parse(localStorage.getItem('sacola'));
    res = false
    sacola.forEach(produto=>{
        if(produto.id === id){
            res = true;
        }
    })
    return res;
}
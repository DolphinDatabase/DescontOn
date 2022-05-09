var lista = [];

async function cadastrar(e){
    e.preventDefault();
    const nome = $("#nome")[0].value;
    const condicao = $("#condicao")[0].value;
    const acao = $("#acao")[0].value;
    var data = {
        nome,
        condicao,
        acao
    }
    var forms = document.getElementsByClassName('needs-validation');
    Array.prototype.filter.call(forms, async function(form){
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        }
        else{   
            $("#myModal").modal("toggle")
            if(id!=null){
                data.id = id;
                await axios.post("/promocao",data)
                .then(async res=>{
                    await axios.post("/itensPromocao/"+id,lista)
                    .then(()=>{
                        $("#modalContent").text("Promoção atualizada com sucesso")
                    })
                })
            }else{
                console.log("nao tem id")
                axios.post("/promocao",data)
                .then(async res=>{
                    console.log(res.data.id)
                    console.log(lista)
                    if(lista.length>0){
                        await axios.post("/itensPromocao/"+res.data.id,lista)
                        .then(()=>{
                            $("#modalContent").text("Promoção cadastrada com sucesso")
                        })
                    }else{
                        $("#modalContent").text("Promoção cadastrada com sucesso")
                        $("#modalClose").click(()=>{window.location.href="/administrativo/produto"})
                    }
                })
            }
        }
    });
}

async function loadPromocao(id){
    const promo = await axios.get("/promocao?id="+id)
    const produto = await axios.get("/itensPromocao/promocao/"+id)
    $("#nome")[0].value = promo.data[0].nome;
    $("#condicao")[0].value = promo.data[0].condicao;
    $("#acao")[0].value = promo.data[0].acao;
    produto.data.forEach(pro=>{
        lista.push(pro);
    })
    adicionarProduto();
}

async function abrirLista(){
    $("#myLista").modal("toggle")
}

function adicionarProduto(produto){
    if(produto!=null){
        lista.push(produto);
    }
    populaParticipante();
    $("#produtoTable").prop('style').removeProperty('display');
    populaTabela()
}

function existeNaLista(produtoId){
    var res = false
    lista.some(element => {
        if (element.id === produtoId) {
          res = true;
        }
    });
    return res;
}

function removeProduto(produtoId){
    var removeIndex = lista.map(produto => produto.id).indexOf(produtoId);
    lista.splice(removeIndex, 1);
    populaParticipante();
    populaTabela()
}

function populaParticipante(){
    $("#listaParticipante").empty();
    lista.forEach(item=>{
        var elemento = '<tr><td class="text-left">'+item.nome+'</td><td class="text-right"><a class="btn btn-sm btn-danger"role="button" onclick="removeProduto('+item.id+')"><i class="oi oi-trash"></i></a></td></tr>';
        $("#listaParticipante").append(elemento)
    })
}

async function populaTabela(){
    $("#listaProduto").empty()
    const res = await axios.get("/produto");
    res.data.forEach(produto=>{
        if(!existeNaLista(produto.id)){
            var elemento = "<tr><td>"+produto.id+"</td><td>"+produto.nome+"</td><td>"+produto.categoria+"</td><td>"+produto.valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</td>";
            elemento += "<td><a class='btn btn-info btn-sm editar' role='button' onclick='adicionarProduto("+JSON.stringify(produto)+")'><span class='bx bx-plus' title='Editar' aria-hidden='true'></span></a></td></tr>"
            $("#listaProduto").append(elemento)
        }
    })
}
var lista = [];
var acao = [
     {
        valor:'[DescontoProduto]',
        selecionado: false
    },
    {
        valor:'[Ganhe]',
        selecionado: false
    },
    {
        valor:'[ItemMenorValor]',
        selecionado: false
    }
]
var condicao = [
    {
        valor:'[ProdutoSelecionado]',
        selecionado: false
    },
    {
        valor:'[ProdutoCategoria]',
        selecionado: false
    },
    {
        valor:'[ProdutoQuantidade >]',
        selecionado: false
    },
    {
        valor:'[ProdutoQuantidade <]',
        selecionado: false
    },
    {
        valor:'[ProdutoQuantidade =]',
        selecionado: false
    },
    {
        valor:'[ProdutoValor >]',
        selecionado: false
    },
    {
        valor:'[ProdutoValor <]',
        selecionado: false
    },
    {
        valor:'[ProdutoValor =]',
        selecionado: false
    }
]

async function cadastrar(e){
    e.preventDefault();
    const nome = $("#nome")[0].value;
    const condicao = serializeCondicao()
    const acao = serializeAcao()
    var data = {
        nome,
        condicao,
        acao
    }
    console.log(data)
    var forms = document.getElementsByClassName('needs-validation');
    Array.prototype.filter.call(forms, async function(form){
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        }
        else{ 
            if(lista.length>0){  
                if(condicao.charAt(0)=="["){
                    if(acao.charAt(0)=="["){
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
                    }else{
                        $("#acaoError").css("display","inherit")
                    }
                }else{
                    $("#condicaoError").css("display","inherit")
                }
            }else{
                $("#produtoError").css("display","inherit")
            }
        }
    });
}


function populaSelect(){
    $("#selectCondicao").empty()
    $("#selectCondicao").append('<option selected disabled id="discond">Selecione uma opção de condição</option>')
    condicao.forEach((condi,index)=>{
        if(!condi.selecionado){
            $("#selectCondicao").append("<option value="+index+">"+condi.valor+"</option>")
        }
    })
    $("#selectAcao").empty()
    $("#selectAcao").append('<option selected disabled id="discond">Selecione uma opção de ação</option>')
    acao.forEach((acao,index)=>{
        if(!acao.selecionado){
            $("#selectAcao").append("<option value="+index+">"+acao.valor+"</option>")
        }
    })
}

function getCondicaoIndex(cond){
    for(let i=0;i<condicao.length;i++){
        if(condicao[i].valor == cond){
            return i;
        }
    }
}

function getAcaoIndex(ac){
    for(let i=0;i<acao.length;i++){
        if(acao[i].valor == ac){
            return i;
        }
    }
}

function deserializeCondicao(cond){
    cond = cond.split("\n")
    objCond = []
    console.log(cond)
    cond.forEach(c=>{
        if(c!=""){
            c = c.split("]")
            for(let i=0;i<c.length;i++){
                if(c[i].substring(0,1)=="["){
                    c[i] = c[i].substring(1)
                }
            }
            if(c[0]=="&&"||c[0]=="||"){
                objCond.push({id:getCondicaoIndex("["+c[1]+"]"),op:c[0],condicao:c[1],valor:c[2]})
            }else{
                if(c[0]!="[ProdutoSelecionado]"){
                    objCond.push({id:getCondicaoIndex("["+c[0]+"]"),op:null,condicao:c[0],valor:c[1]})
                }else{
                    objCond.push({id:getCondicaoIndex("["+c[0]+"]"),op:null,condicao:c[0],valor:null})
                }
            }
        }
    })
    $("#selectCondicao").css("display","none")
    objCond.forEach(obj=>{
        condicao[obj.id].selecionado=true
        var div = '<div class="d-flex mb-2" id="condicao'+obj.id+'">'
        if(obj.op!=null){
            div += '<select class="form-select" style="width:100px" id="selectOperador">'
            div += (obj.op=="&&")?'<option value="&&" selected>e</option>':'<option value="&&">e</option>'
            div += (obj.op=="||")?'<option value="||" selected>ou</option>':'<option value="||">ou</option>'
            div += '</select>'
        }
        div += '<div style="display: flex; background: #EA3D6B;align-items: center;color: white;padding: 5px;">'
        div += '<p style="margin: 0;width: max-content">['+obj.condicao+']</p>'
        div += '<span style="margin-left: 5px;" onclick="removeCondicao(`'+obj.id+'`)"><i class="bx bx-x nav_icon"></i> </span>'
        div += '</div>'
        if(obj.condicao!="ProdutoSelecionado"){
            condicao[0].selecionado=true
            if(obj.condicao=="ProdutoCategoria"){
                div += '<select class="form-select" id="proCategoria" aria-label="Default select example">'
                div += (obj.valor=='Cosmeticos')?'<option value="Cosmeticos" selected>Cosméticos</option>':'<option value="Cosmeticos">Cosméticos</option>'
                div += (obj.valor=='Perfumaria')?'<option value="Perfumaria" selected>Perfumaria</option>':'<option value="Perfumaria" selected>Perfumaria</option>'
                div += (obj.valor=='Saude')?'<option value="Saude" selected>Saúde</option></select>':'<option value="Saude">Saúde</option></select>'
            }else{
                div += '<input type="number" class="form-control condicao-input" placeholder="When" onkeyup="habilitarBotao(event)" value="'+obj.valor+'"required/></div>'
            }
        }else{
            $("#addCondicao").css("display","none")
        }
        div += "</div>"
        $("#condicao").append(div)
    })
    if(objCond.length<2 && objCond[0].condicao!="ProdutoSelecionado"){
        $("#addCondicao").css("display","block")
    }else{
        $("#addCondicao").css("display","none")
    }
}

function deserializeAcao(ac){
    ac = ac.split("]")
    ac[0] = ac[0]+"]"
    var id = getAcaoIndex(ac[0])
    var div = '<div class="d-flex mb-2" id="acao'+id+'">'
    div += '<div style="display: flex; background: #EA3D6B;align-items: center;color: white;padding: 5px;">'
    div += '<p style="margin: 0;width: max-content">'+ac[0]+'</p>'
    div += '<span style="margin-left: 5px;" onclick="removeAcao(`'+id+'`)"><i class="bx bx-x nav_icon"></i> </span>'
    div += '</div>'
    if(ac[0] == "[DescontoProduto]" || ac[0] == "[Ganhe]" || ac[0] == "[ItemMenorValor]"){
        div += '<input type="number" class="form-control acao-input" placeholder="Then" value="'+ac[1]+'" required/></div>'
    }else{
        div += '</div>'
    }
    acao[id].selecionado=true
    $("#acao").append(div)
    $("#selectAcao").css("display","none")
    $("#addAcao").css("display","none")
}

async function loadPromocao(id){
    const promo = await axios.get("/promocao?id="+id)
    const produto = await axios.get("/itensPromocao/promocao/"+id)
    $("#nome")[0].value = promo.data[0].nome;
    deserializeCondicao(promo.data[0].condicao)
    deserializeAcao(promo.data[0].acao)
    produto.data.forEach(pro=>{
        lista.push(pro);
    })
    adicionarProduto();
    populaSelect()
}

async function abrirLista(){
    $("#myLista").modal("toggle")
}

function adicionarProduto(produto){
    if(produto!=null){
        lista.push(produto);
    }
    $("#produtoError").css("display","none")
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
        if(!existeNaLista(produto.id) && produto.status==0){
            var elemento = "<tr><td>"+produto.id+"</td><td>"+produto.nome+"</td><td>"+produto.categoria+"</td><td>"+produto.valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</td>";
            elemento += "<td><a class='btn btn-info btn-sm editar' role='button' onclick='adicionarProduto("+JSON.stringify(produto)+")'><span class='bx bx-plus' title='Editar' aria-hidden='true'></span></a></td></tr>"
            $("#listaProduto").append(elemento)
        }
    })
}
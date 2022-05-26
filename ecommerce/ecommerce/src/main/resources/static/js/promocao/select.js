//condicao
function verificarCondicao(promoId){
    var item = condicao[promoId]
    $("#selectCondicao").css("display","none")
    var div = '<div class="d-flex mb-2" id="condicao'+promoId+'">'
    var count = $("#condicao").children().length
    if(count>0){
        div += '<select class="form-select" style="width:100px" id="selectOperador"><option value="&&">e</option><option value="||">ou</option></select>'
    }else{
        if(item.valor!="[ProdutoSelecionado]"){
            condicao[0].selecionado=true
        }
    }
    div += '<div style="display: flex; background: #EA3D6B;align-items: center;color: white;padding: 5px;">'
    div += '<p style="margin: 0;width: max-content">'+item.valor+'</p>'
    div += '<span style="margin-left: 5px;" onclick="removeCondicao(`'+promoId+'`)"><i class="bx bx-x nav_icon"></i> </span>'
    div += '</div>'
    if(item.valor != "[ProdutoSelecionado]"){
        if(item.valor=="[ProdutoCategoria]"){
            div += '<select class="form-select" id="proCategoria" aria-label="Default select example">'
            div += '<option value="Cosmeticos">Cosméticos</option>'
            div += '<option value="Perfumaria">Perfumaria</option>'
            div += '<option value="Saude">Saúde</option></select>'
        }else{
            div += '<input type="number" class="form-control condicao-input" placeholder="When" onkeyup="habilitarBotao(event)" required/></div>'
            $("#addCondicao").attr("disabled",true)
        }
        condicao[promoId].selecionado=true
        $("#discond").prop('selected',true)
        if($("#condicao").children().length<1){
            $("#addCondicao").css("display","block")
        }else{
            $("#addCondicao").css("display","none")
        }
    }else{
        $("#addCondicao").css("display","none")
        div += '</div>'
    }
    $("#condicao").append(div)
    populaSelect()
}

function adicionarCondicao(e){
    e.preventDefault()
    $("#selectCondicao").css("display","block")
    $("#addCondicao").css("display","none")
}

function habilitarBotao(event){
    var inputs = $(".condicao-input")
    if(inputs.length>0){
        for(let input of inputs){
            if(input.value == "" || input.value == null){
                $("#addCondicao").attr("disabled",true)
                break
            }else{
                $("#addCondicao").attr("disabled",false)
            }
        }
    }
}

function serializeCondicao(){
    var data = ""
    var children = $("#condicao").children()
    for(let child of children){
        var op = $(child).find("#selectOperador")[0]
        if(op){
            data += "["+op.value.toUpperCase()+"]"
        }
        var condicao = $(child).find("p").text()
        data += condicao
        if(condicao!="[ProdutoSelecionado]"){
            if(condicao == "[ProdutoCategoria]"){
                data += $(child).find("#proCategoria")[0].value 
            }else{
                data += $(child).find("input")[0].value
            }
        }
        data += "\n"
    }
    return data
}

function serializeAcao(){
    var child = $("#acao").children()[0]
    return $(child).find("p").text()+$(child).find("input")[0].value
}

//acao
function verificarAcao(event){
    var item = acao[event.target.value]
    $("#selectAcao").css("display","none")
    var div = '<div class="d-flex mb-2" id="acao'+event.target.value+'">'
    div += '<div style="display: flex; background: #EA3D6B;align-items: center;color: white;padding: 5px;">'
    div += '<p style="margin: 0;width: max-content">'+item.valor+'</p>'
    div += '<span style="margin-left: 5px;" onclick="removeAcao(`'+event.target.value+'`)"><i class="bx bx-x nav_icon"></i> </span>'
    div += '</div>'
    if(item.valor == "[DescontoProduto]" || item.valor == "[Ganhe]" || item.valor == "[ItemMenorValor]"){
        div += '<input type="number" class="form-control acao-input" placeholder="Then" required/></div>'
    }else{
        div += '</div>'
    }
    acao[event.target.value].selecionado=true
    $("#discond").prop('selected',true)
    $("#acao").append(div)
    populaSelect()
}

function removeCondicao(id) {
    $("#condicao"+id).remove()
    condicao[id].selecionado=false
    var c = serializeCondicao().split("\n")
    if(c[0].substring(0,4)=="[&&]" || c[0].substring(0,4)=="[||]"){
        $($("#condicao").children()[0]).children().get(0).remove()
    }
    c = c.filter(function (el) {
        return el != "";
    });
    console.log(c)
    if(c.length==0 || c[0]==''){
        condicao[0].selecionado=false
        $("#selectCondicao").css("display","block")
        $("#addCondicao").css("display","none")
    }else{
        if(c.length<=1){
            $("#addCondicao").css("display","block")
            $("#addCondicao").attr("disabled",false)

        }else{
            $("#addCondicao").css("display","none")
        }
    }
    populaSelect()
}

function removeAcao(id) {
    $("#acao"+id).remove()
	acao[id].selecionado=false
	$("#selectAcao").css("display","block")
    populaSelect()
}
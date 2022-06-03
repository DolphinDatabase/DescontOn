document.addEventListener("DOMContentLoaded", function(event) {
    verificaDesconto();
    verificarBotao();
});

var desconto = []

function populaTabelaDesconto(){
    if(desconto.length>0){
        desconto = desconto.sort(function(a, b){ 
            if (a.total > b.total) {
                return -1;
            }  
        });
        desconto[0].checked=true
        $("#listaPromocaao").empty()
        for(let i = 0; i < desconto.length; i++){
            var div = "<tr><td>"+desconto[i].id+"</td><td>"+desconto[i].promocao+"</td><td>"
            div += (desconto[i].checked)?"<input class='form-check-input' name='opDesconto' type='radio' id='opDesconto' checked onchange='mudarPromocao("+desconto[i].id+")'></td></tr>":"<input class='form-check-input' name='opDesconto' type='radio' id='opDesconto' onchange='mudarPromocao("+desconto[i].id+")'></td></tr>"
            $("#listaPromocaao").append(div)
        }
    }
}

function mudarPromocao(id){
    desconto.forEach(d=>{d.checked=false})
    $(desconto).filter((i,n)=>{return n.id==id})[0].checked=true
    popularTabela()
}


async function verificaDesconto(){
    var sacola = JSON.parse(localStorage.getItem('sacola'));
    var total = 0
    sacola.forEach(item=>{
        total += item.valor*item.quantidade
    })
    const res = await axios.post("/sacola/verificarDesconto",sacola)
    desconto = []
    for (const [key, value] of Object.entries(res.data)) {
        await axios.post("/sacola/aplicarDesconto",{
            sacola:value,
            total:total,
            condicao:key.split(", ")[2],
            acao:key.split(", ")[3]
        })
        .then(res=>{
            var total = 0
            res.data.forEach(i=>{
                total += i.desconto
            })
            desconto.push({id:key.split(", ")[0],promocao:key.split(", ")[1],desc:res.data,total:total,checked:false})
        })
    }
    populaTabelaDesconto()
    popularTabela()
}

async function popularTabela(){
    if(localStorage.getItem('sacola')){
        $("#tableBody").empty();
        var sacola = JSON.parse(localStorage.getItem('sacola'));
        var table = ""
        var subtotal = 0;
        var lst = $(desconto).filter((i,n)=>{return n.checked})[0]

        sacola.forEach(produto => {
            subtotal += produto.valor*produto.quantidade
            var desc = $(lst.desc).filter((i,n)=>{return n.id==produto.id})[0]
            table += "<tr><td>"+produto.nome+"</td><td class='text-right'></td>";
            table += "<td><a class='btn btn-sm btn-light' role='button' align='center' onclick='mudarQtd("+produto.id+",1)'><i class='oi oi-plus'></i></a> &nbsp";
			table += "<span class='text-center'>"+produto.quantidade+"</span>";
			table += "&nbsp; <a class='btn btn-sm btn-light' role='button' onclick='mudarQtd("+produto.id+",-1)'><i class='oi oi-minus'></i></a></td>";
			table += "<td scope='col' ></td><td scope='col'>"+produto.valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</td>";
			table += "<td class='text-right'>"+(produto.valor*produto.quantidade).toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</td>";
			if(desc!=undefined){
                table += "<td class='text-right'>"+(desc.desconto).toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</td>";
            }else{
                table += "<td class='text-right'>"+(0.0).toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</td>";
            }
            table += "<td class='text-right'><a class='btn btn-sm btn-danger' role='button' onclick='removerSacola("+produto.id+")'><i class='oi oi-trash'></i></a></td></tr>";
        });
        var descontoTotal = 0
        if(desconto.length>0){
            lst.desc.forEach(x=>{
                descontoTotal+=x.desconto
            })
        }
        var total = subtotal - descontoTotal
        table += "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td>Sub-Total</td><td class='text-right'><span>"+subtotal.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</span></td></tr>";
        table += "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td>Desconto</td><td class='text-right'>"+descontoTotal.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"<span onclick='$(`#descontoModal`).modal(`toggle`)'><i class='bx bxs-info-circle text-primary'></i></span></td></tr>";
        table += "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td><strong>Total</strong></td><td class='text-right'><strong><span>"+total.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</span></strong></td></tr>"
        $("#tableBody").append(table);
    }else{
        total = 0;
        var table = "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td>Sub-Total</td><td class='text-right'><span>"+total.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</span></td></tr>";
        table += "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td>Desconto</td><td class='text-right'>"+total.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</td></tr>";
        table += "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td><strong>Total</strong></td><td class='text-right'><strong><span>"+total.toLocaleString('pt-br',{style: 'currency', currency: 'BRL',minimumFractionDigits: 2})+"</span></strong></td></tr>"
        $("#tableBody").append(table);
    }
}

async function openinfo(){
    var sacola = JSON.parse(localStorage.getItem('sacola'))
    console.log(sacola)
    await axios.post("/sacola/verificarDesconto",sacola)
    .then(res=>{
        console.log(res.data)
    })
}

function verificarBotao(){
    var btn = document.getElementById("btn-finalizar");
    if(!localStorage.getItem('sacola') || JSON.parse(localStorage.getItem('sacola')).length<=0){
        btn.style.pointerEvents = "none";
        btn.style.cursor = "not-allowed";
    }else{
        btn.style.cursor = "default";
        btn.style.pointerEvents = null;
    }
}

function mudarQtd(id,act){
    var sacola = JSON.parse(localStorage.getItem('sacola'))
    for(let i=0; i<sacola.length; i++){
        if(sacola[i].id == id){
            sacola[i].quantidade += act
            if(sacola[i].quantidade <= 0){
                removerSacola(sacola[i].id)
            }else{
                localStorage.setItem('sacola',JSON.stringify(sacola))
                verificaDesconto()
            }
        }
    }
}

function removerSacola(id){
    var sacola = JSON.parse(localStorage.getItem('sacola'))
    var removeIndex = sacola.map(produto => produto.id).indexOf(id);
    sacola.splice(removeIndex, 1);
    localStorage.setItem('sacola',JSON.stringify(sacola))
    verificaDesconto()
    verificarBotao()
}

async function verificarCompra(){
    var sacola = JSON.parse(localStorage.getItem('sacola'))
    var data = []
    var erros = []
    sacola.forEach(produto=>{
        data.push(produto.id)
    })
    await axios.post("/produto/verificar",data)
    .then(res=>{
        sacola.forEach(pro=>{
            var err = []
            let find = false
            console.log(pro)
            for(ver of res.data){
                if(ver.id==pro.id){
                    console.log(ver)
                    find = true
                    if(ver.nome === pro.nome){
                        if(ver.valor === pro.valor){
                            if(ver.categoria === pro.categoria){
                                if(ver.status === 1){
                                    err.push("o produto não esta mais disponível")
                                }else{
                                    break
                                }
                            }else{
                                err.push("a categoria do produto foi aterada")
                            }
                        }else{
                            err.push("o valor do produto foi alterado")
                        }
                    }else{
                        err.push("o nome do produto foi alterado")
                    }
                }
            }
            if(find == false){
                err.push("o produto não existe mais")
            }
            if(err.length>0){
                erros.push({produto:pro.nome+" - "+pro.id,erros:err})
            }
        })
        if(erros.length>0){
            $("#myModal").modal("toggle")
            var lista = ""
            erros.forEach(erro=>{
                lista += "<p>"+erro.produto+"</p><ul>"
                erro.erros.forEach(err=>{
                    lista += "<li>"+err+"</li>"
                })
                lista += "</ul>"
            })
            $("#modalContent").append(lista)
        }else{
            finalizar(sacola)
        }
    })
}

function cancelar(){
    localStorage.removeItem('sacola');
    window.location.href = "/"  
}

async function finalizar(sacola) {
    await axios.post("/sacola",{
        id: null,
        valorTotal: total
    })
    .then(async res=>{
        data = []
        sacola.forEach(produto=>{
            data.push({
                id:null,
                produto:{
                    id:produto.id,
                    nome:produto.nome,
                    valor: produto.valor,
                    categoria:produto.categoria,
                    status:produto.status
                },
                compra:res.data,
                quantidade:produto.quantidade,
                valorUnitario: produto.valor,
                valorTotal: (produto.valor*produto.quantidade)
            })

        })
        await axios.post("/itenscompra",data)
        .then(()=>{
            $("#myModal").modal("toggle")
            $("#modalContent").append("<p>Compra finalizada com sucesso")
        })
        .catch(err=>{
            console.error(err)
        })
    })
    .catch(err=>{
        console.error(err)
    })

}
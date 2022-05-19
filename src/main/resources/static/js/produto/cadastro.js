function saveProduto(event){
    event.preventDefault();
    const proNome = $("#proNome")[0].value;
    const proCategoria = $("#proCategoria")[0].value;
    const proValor = $("#proValor")[0].value;
    var data = {
        id:null,
        nome:proNome,
        valor:proValor,
        categoria:proCategoria,
        status:0
    }
    var forms = document.getElementsByClassName('needs-validation');
    Array.prototype.filter.call(forms, function(form){
        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        }
        else{   
            $("#myModal").modal("toggle")
            if(id){
                data.id = id;
                axios.post("/produto",data)
                .then(res=>{
                    $("#modalContent").text("Produto atualizado com sucesso")
                    $("#modalClose").click(()=>{window.location.href="/administrativo/produto/listagem"})
                }).catch(err=>{
                    console.error(err)
                });
            }else{
                axios.post("/produto",data)
                .then(res=>{
                    $("#modalContent").text("Produto cadastrado com sucesso")
                    $("#modalClose").click(()=>{window.location.href="/administrativo/produto"})
                })
                .catch(err=>{
                    console.error(err);
                });
            }
        }
    });
}

async function loadProduto(id){
    const res = await axios.get("/produto?id="+id);
    $("#proNome")[0].value=res.data[0].nome;
    $("#proCategoria")[0].value=res.data[0].categoria;
    $("#proValor")[0].value=res.data[0].valor;
}
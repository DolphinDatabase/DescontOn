function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.innerText);
    ev.dataTransfer.setData("target", ev.target.getAttribute("target"))
}

const isAllowed = (idTarget,id)=>{
    if(idTarget==("drop"+id)){
        return true;
    }else{
        return false;
    }
}
  
function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var target = ev.dataTransfer.getData("target")
    if(isAllowed(ev.target.id,target)){
        if(data == "[ProdutoSelecionado]"){
            $("#drop"+target).css("display","none")
            $("#drop"+target).removeAttr("required")
        }
        $("#"+target+"Error").css("display","none")
        $("#drop"+target).attr("type","number")
        $("#btn"+target).css("display","none")
        $("#op"+target).text(data)
        $("#show"+target).css("display","flex")
    }
}
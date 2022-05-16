function prepareDrag(el) {
    el.addEventListener('dragstart', e => e.dataTransfer.setData('text/plain', el.textContent))
}
[...document.querySelectorAll('button')].forEach(prepareDrag);

var textarea = document.querySelector('textarea');

textarea.addEventListener('dragover', e => e.preventDefault());
textarea.addEventListener('drop', e => {
    var data = e.dataTransfer.getData('text/plain');
    console.log(data);
    textarea.value += data;
});
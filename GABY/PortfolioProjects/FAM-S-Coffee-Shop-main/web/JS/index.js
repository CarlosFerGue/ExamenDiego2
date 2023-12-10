/*Seleccionamos los divs que van a ejecutar una animación*/
const bg2 = document.querySelector('#bg2');
const title2 = document.querySelector('#title2');
const text2 = document.querySelector('#text2');
const logo_bg2 = document.querySelector('#logo_bg2');
const bg3 = document.querySelector('#bg3');
const title3 = document.querySelector('#title3');
const text3 = document.querySelector('#text3');
const stars = document.querySelector('#stars');

/*Eliminamos la etiueta de ".reveal", de fotma que todos los elementos se quedadrán en la posicion/etiqueta previa a la animación*/
bg2.classList.remove('reveal');
title2.classList.remove('reveal');
text2.classList.remove('reveal');
logo_bg2.classList.remove('reveal');
window.addEventListener('scroll' , mostrar);
bg3.classList.remove('reveal');
title3.classList.remove('reveal');
text3.classList.remove('reveal');
stars.classList.remove('reveal');
window.addEventListener('scroll' , mostrar);

/*Función que añade el "reveal" a los elementos para que se ejecute la animación cuando */
function mostrar(){
    const trigger = window.innerHeight / 3 * 2;
    const boxTop = title2.getBoundingClientRect().top;
    if (boxTop < trigger){
        bg2.classList.add('reveal');
        title2.classList.add('reveal');
        text2.classList.add('reveal');
        logo_bg2.classList.add('reveal');
        bg3.classList.add('reveal');
        title3.classList.add('reveal');
        text3.classList.add('reveal');
        stars.classList.add('reveal');
    }
}

let nick = document.querySelector('#nick')
nick.innerHTML = localStorage.getItem("user")
function transicionar(){
    const r1 = document.querySelector('#r1');
    const r2 = document.querySelector('#r2');
    r1.classList.add('hide');
    r2.classList.remove('hide');
    
    const nombre = document.querySelector('#nombre').value;
    const correo = document.querySelector('#correo').value;
    //Obtenemsos el valor seleccionado en el "radio buttons" de GENERO.
    var genero = "";
    const radios = document.getElementsByName('genero');
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            genero = radios[i].value;
            break;
        }
    }
    const fecha_nacimiento = document.querySelector('#fecha_nacimiento').value;
    const biografia = document.querySelector('#biografia').value;

    r2.innerHTML = `
        <h1>RESUMEN DE USUARIO</h1>
        <p><b>Nombre de usuario: </b>${nombre}.</p>
        <p><b>Correo: </b>${correo}.</p>
        <p><b>Género: </b>${genero}.</p>
        <p><b>Nacimiento: </b>${fecha_nacimiento}.</p>
    `;
    if (biografia!=""){
        r2.innerHTML += `<p><b>Descripción: </b>${biografia}.</p>`;
    }
}

function comprobar(){
    let comprobar = 1;
    var error = "";

    const nombre = document.querySelector('#nombre').value;
    const correo = document.querySelector('#correo').value;
    //Obtenemsos el valor seleccionado en el "radio buttons" de GENERO.
    var genero = "";
    const radios = document.getElementsByName('genero');
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            genero = radios[i].value;
            break;
        }
    }
    const fecha_nacimiento = document.querySelector('#fecha_nacimiento').value;
    //Comprobamos que los campos obligatorios no esten vacíos.
    if(nombre=="" || correo=="" || genero=="" || fecha_nacimiento==""){
        comprobar = 0;
        error += "Por favor, rellene/seleccione los campos obligatorios.\n"
    }
    //Comprobamos que el correo sea válido (damos como valido que contenga un "@" y un ".").
    const correo_valido = /.+@[^.]+\..+/.test(correo);
    if (correo_valido==false){
        comprobar = 0;
        error += "Introduzca un correo válido.\n";
    }
    //Mostramos errores o transicionamos a mostrar la info.
    if (comprobar==1){
        transicionar();
    } else{
        alert(error);
    }

}
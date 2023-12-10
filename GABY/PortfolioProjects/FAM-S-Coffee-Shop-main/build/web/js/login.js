var resultados = []
var all = []

async function obtenerResultados() {
  const response = await fetch("Controller?ACTION=LOGIN.LOGIN_STAFF", {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
  });
  resultados = await response.json();
  return resultados;
}

document.querySelector('#button').addEventListener('click', async ()=>{
  event.preventDefault();
  const username = document.querySelector('#username').value;
  const password = document.querySelector('#password').value;
  const resultados = await obtenerResultados()
  for (let i = 0; i < resultados.length; i++) {
    if(resultados[i].nombre == username){
      if(resultados[i].contrasena == password){
        document.querySelector('#login_error').style.display = "none";
        localStorage.setItem("user", username);
        localStorage.setItem("pass", password);
        window.location.href = "http://localhost:8080/FAM-S-Coffee-Shop/staff.html"
        break
      }else{
        document.querySelector('#login_error').style.display = "block";
      }
    }else{
      document.querySelector('#login_error').style.display = "block";
    }
  }
})


async function checkLogin(){
  const respuesta = await fetch("Controller?ACTION=LOGIN.LOGIN_STAFF", {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
});
    all = await respuesta.json();
    all.forEach(element => {
      if(localStorage.getItem("user") == element.nombre){
        if(localStorage.getItem("pass") == element.contrasena){
          window.location.href = "http://localhost:8080/FAM-S-Coffee-Shop/staff.html"
        }
      }
    });
}

checkLogin()






















/*// Obtener referencias a los elementos HTML del formulario
const form = document.getElementById('form');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');

// Manejar el envío del formulario
form.addEventListener('submit', async (event) => {
  // Evitar que el formulario se envíe automáticamente
  event.preventDefault();
  
  // Obtener los valores de entrada del usuario
  const username = usernameInput.value;
  const password = passwordInput.value;

  // Enviar una solicitud al servidor con los detalles de inicio de sesión
  const response = await fetch('url_de_tu_api', {
    method: 'POST',
    body: JSON.stringify({ username, password }),
    headers: {
      'Content-Type': 'application/json'
    }
  });

  // Analizar la respuesta JSON del servidor
  const data = await response.json();

  // Comprobar si el inicio de sesión es exitoso
  if (data.success) {
    // Mostrar una alerta de inicio de sesión exitoso
    alert('Has iniciado sesión exitosamente');
  } else {
    // Mostrar un mensaje de error si el inicio de sesión no es exitoso
    alert('Error: ' + data.message);
  }
});*/
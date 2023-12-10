const form = document.querySelector('#form');
const usernameInput = document.querySelector('#username');
const passwordInput = document.querySelector('#password');
const emailInput = document.querySelector('#email');
var all = []

form.addEventListener('submit', async (event) => {
  event.preventDefault();
  const username = usernameInput.value;
  const password = passwordInput.value;
  const email = emailInput.value;
  const response = await fetch("Controller?ACTION=LOGIN.FIND_ALL", {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });
  const resultados = await response.json();
  let emailEncontrado = false
  resultados.forEach(element => {
    if (element.email === email) {
      emailEncontrado = true;
    }
  });
  if (emailEncontrado) {
    document.querySelector('#login_error').style.display = "block";
    return;
  }else{
    document.querySelector('#login_error').style.display = "none";
  }
  const id = resultados.length + 1;
  const send = await fetch("Controller?ACTION=LOGIN.REGISTER&ID=" + id + "&NOMBRE=" + username + "&CORREO=" + email + "&CONTRASENA=" + password, {
    method: 'GET'
  });
  localStorage.setItem("user", username);
  localStorage.setItem("pass", password);
  window.location.href = "http://localhost:8080/FAM-S-Coffee-Shop/loginUser.html"
});


async function checkLogin(){
  const respuesta = await fetch("Controller?ACTION=LOGIN.FIND_ALL", {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
});
    all = await respuesta.json();
    all.forEach(element => {
      if(localStorage.getItem("user") == element.nombre){
        if(localStorage.getItem("pass") == element.contrasena){
          window.location.href = "http://localhost:8080/FAM-S-Coffee-Shop/loginUser.html"
        }
      }
    });
}

checkLogin()
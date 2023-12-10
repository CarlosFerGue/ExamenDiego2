var resultados = []
var all = []

async function obtenerResultados() {
  const response = await fetch("Controller?ACTION=LOGIN.LOGIN_USUARIO", {
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
        window.location.href = "http://localhost:8080/FAM-S-Coffee-Shop/products.html"
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
            window.location.href = "http://localhost:8080/FAM-S-Coffee-Shop/products.html"
          }
        }
      });
}

checkLogin()
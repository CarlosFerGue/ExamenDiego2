listar_json()
var items;
//LISTADO DE LOS PRODUCTOS EN BASE A UN JSON DE PRUEBA
async function listar_json() {
    items = await filtrarProductos()
    // Ahora, los datos del archivo JSON están almacenados en la variable 'data'.

    let listado = document.querySelector('.items')
    listado.innerHTML = "";

    //Variable que usaremos para obtener subtotal, shipping y el.
    var total_item = 0.00;
    var subtotal = 0.00;
    var shipping = 0.00;
    var total = 0.00;
    var cantidad = 1;
    // Recorremos el JSON con un forEach para ir creando y listando cada elemento.
    for (let i = 0; i < items.length; i++) {
        const item = items[i];
        total_item = item.precio * item.cantidad;
        const producto = document.createElement('div');
        producto.classList.add('item');
        producto.setAttribute("id", item.id);

        const quantityInput = document.createElement('input');
        quantityInput.type = 'text';
        quantityInput.value = cantidad;
        quantityInput.classList.add('quantity');
        quantityInput.addEventListener('input', actualizarTotal);
        quantityInput.onkeydown = validarTecla;

        const deleteButton = document.createElement('img');
        deleteButton.src = "./IMG/logos/delete_button.png";
        deleteButton.classList.add('delete_button');
        deleteButton.addEventListener('click', () => {
            delete_item(item.id)
            actualizarTotal
        });
        producto.innerHTML += `<div class="item_desc"><img src="` + item.url + `" class="item_pic">` + item.nombre + `</div>
                                <div class="item_price">` + item.precio + `€</div>
                                <div class="item_quantity">
                                </div>
                                <div class="item_total">` + item.precio * cantidad + `€</div>
                                <img src="./IMG/logos/delete_button.png" class="delete_button" onclick="delete_item(` + item.id + `)">
                              <div class="hr_items"></div>`;
        listado.appendChild(producto);
        producto.querySelector('.item_quantity').appendChild(quantityInput);
        subtotal += parseFloat(total_item.toFixed(2));
        console.log(subtotal.toFixed(2));
        //document.querySelector(".quantity").addEventListener('input', actualizarTotal)
    }
    function actualizarTotal() {
        subtotal = 0.00;

        for (let i = 0; i < items.length; i++) {
            const item = items[i];
            const quantityInput = document.getElementById(item.id).querySelector('.quantity');
            const itemTotal = document.getElementById(item.id).querySelector('.item_total');
            item.cantidad = parseInt(quantityInput.value);
            itemTotal.textContent = (item.precio * item.cantidad).toFixed(2) + '€';
            subtotal += item.precio * item.cantidad;
            if (quantityInput.value === "" || isNaN(quantityInput.value) || parseInt(quantityInput.value) <= -1) {
                alert("Por favor, introduce un número positivo válido.");
                var valorActual = quantityInput.value;
                quantityInput.value = 1;

                // Restaurar el valor actual si se dejó vacío
                if (valorActual === "") {
                    quantityInput.value = valorActual;
                }
            }

        }


        document.getElementById("subtotal_value").textContent = subtotal.toFixed(2) + "€";
        shipping = subtotal * 0.06;
        document.getElementById("shipping_div").textContent = shipping.toFixed(2) + "€";
        total = shipping + subtotal;
        document.getElementById("total_div").textContent = total.toFixed(2) + "€";

    }

    actualizarTotal();
}







function actualizarTotales() {
    subtotal = 0.00;

    for (let i = 0; i < items.length; i++) {
        const item = items[i];
        const quantityInput = document.getElementById(item.id).querySelector('.quantity');
        const itemTotal = document.getElementById(item.id).querySelector('.item_total');
        item.cantidad = parseInt(quantityInput.value);
        itemTotal.textContent = (item.precio * item.cantidad).toFixed(2) + '€';
        subtotal += item.precio * item.cantidad;
        if (quantityInput.value === "" || isNaN(quantityInput.value) || parseInt(quantityInput.value) <= -1) {
            alert("Por favor, introduce un número positivo válido.");
            var valorActual = quantityInput.value;
            quantityInput.value = 1;

            // Restaurar el valor actual si se dejó vacío
            if (valorActual === "") {
                quantityInput.value = valorActual;
            }
        }

    }


    document.getElementById("subtotal_value").textContent = subtotal.toFixed(2) + "€";
    shipping = subtotal * 0.06;
    document.getElementById("shipping_div").textContent = shipping.toFixed(2) + "€";
    total = shipping + subtotal;
    document.getElementById("total_div").textContent = total.toFixed(2) + "€";

}






function validarTecla(event) {
    var tecla = event.which || event.keyCode;
    // Permitir solo números y delete
    if ((tecla < 48 || tecla > 57) && tecla !== 8 && tecla !== 46) {
        event.preventDefault();
    }
}

//Función con la que eliminamos los elementos de la lista por su ID.
function delete_item(id) {
    item = document.getElementById(id);
    console.log(item);
    item.remove();
    //borrar local storage lo de abajo
    var productos = JSON.parse(localStorage.getItem('productos'));
    var index = productos.indexOf(id);
    if (index !== -1) {
        productos.splice(index, 1);
        localStorage.setItem('productos', JSON.stringify(productos));
    }
    borrarHTML()
    listar_json()
    actualizarTotales()
}

function borrarHTML() {
    let listado = document.querySelector('.items')
    listado.innerHTML = ""
}


/**************************************************************/



async function obtenerResultados() {
    const response = await fetch("Controller?ACTION=PRODUCTOS.FIND_ALL", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const resultados = await response.json();
    console.log("Resultados: " + resultados)
    return resultados;
}

async function filtrarProductos() {
    var productos = JSON.parse(localStorage.getItem('productos'));
    const resultados = await obtenerResultados();
    const productosFiltrados = resultados.filter(producto => productos.includes(producto.id));
    console.log("Filtrados: " + productosFiltrados)
    return productosFiltrados;
}

let nick = document.querySelector('#nick')
nick.innerHTML = localStorage.getItem("user")

document.addEventListener('DOMContentLoaded', function () {


    // Obtener referencias a los elementos del DOM
    const nombreInput = document.getElementById('nombre');
    const apellidoInput = document.getElementById('apellido');
    const dniInput = document.getElementById('dni');
    const fechaAltaInput = document.getElementById('fechaAlta');
    const calleInput = document.getElementById('calle');
    const numeroInput = document.getElementById('numero');
    const ciudadInput = document.getElementById('ciudad');
    const departamentoInput = document.getElementById('departamento');

    const nombreOInput = document.getElementById('nombreO');
    const apellidoOInput = document.getElementById('apellidoO');
    const matriculaInput = document.getElementById('matricula');

    const pacienteIdInput = document.getElementById('pacienteId');
    const odontologoIdInput = document.getElementById('odontologoId');
    const fechaTurnoInput = document.getElementById('fechaTurno');

    const idPInput = document.getElementById('idP');
    const idOInput = document.getElementById('idO');
    const idTInput = document.getElementById('idT');

    const listarPButton = document.getElementById('listarP');
    const agregarPButton = document.getElementById('agregarP');
    const buscarPButton = document.getElementById('buscarP');

    const listarOButton = document.getElementById('listarO');
    const agregarOButton = document.getElementById('agregarO');
    const buscarOButton = document.getElementById('buscarO');

    const listarTButton = document.getElementById('listarT');
    const agregarTButton = document.getElementById('agregarT');
    const buscarTButton = document.getElementById('buscarT');

    const contenedorRespuesta = document.getElementById('contenedorRespuesta');
    const status = document.getElementById('status');
    const formPacientes = document.getElementById('formPacientes');
    const formOdontologos = document.getElementById('formOdontologos');
    const formTurnos = document.getElementById('formTurnos');


    // Event listeners para los botones
    listarPButton.addEventListener('click', listarPacientes);
    agregarPButton.addEventListener('click', function (event) {
        event.preventDefault(); // Evitar comportamiento predeterminado del botón
        agregarPaciente();
        });
    buscarPButton.addEventListener('click', function (event) {
        event.preventDefault(); // Evitar comportamiento predeterminado del botón
        buscarPaciente();
    });


    listarOButton.addEventListener('click', listarOdontologos);
    agregarOButton.addEventListener('click', function (event) {
        event.preventDefault(); // Evitar comportamiento predeterminado del botón
        agregarOdontologo();
        });
    buscarOButton.addEventListener('click', function (event) {
        event.preventDefault(); // Evitar comportamiento predeterminado del botón
        buscarOdontologo();
        });


    listarTButton.addEventListener('click', listarTurnos);
    agregarTButton.addEventListener('click', function (event) {
        event.preventDefault(); // Evitar comportamiento predeterminado del botón
        agregarTurno();
        });
    buscarTButton.addEventListener('click', function (event) {
        event.preventDefault(); // Evitar comportamiento predeterminado del botón
        buscarTurno();
        });


    // FUNCIONES GENERALES aplicables a todas las entities.

    // Funciones para enviar los datos vía POST utilizando Fetch
    function enviarDatos(url, datos) {

        //let bodyData = JSON.stringify(datos);

        let jsonString = JSON.stringify(datos);
        let bodyData = jsonString.replace(/"([^"]+)":/g, '"$1":');

        console.log("Datos a enviar: ", bodyData);

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: bodyData
        })
            .then(response => {
                if (response.ok) {

                    console.log('Datos enviados correctamente');

                    // Agregar algo para modificar el HTML con una respuesta, activando un div o similar.
                    status.innerText = "Datos agregados con éxito!"
                    status.classList.add('ok');
                    status.classList.remove('oculto');
                    // ver DELAY
                    setTimeout(function () {
                    console.log("Delay de 4 segundos.");
                    status.classList.add('oculto');
                    }, 2000);

                    return response.json(); // Convertir la respuesta a JSON

                } else {
                    status.innerText = "ERROR al procesar los datos!"
                    status.classList.add('error');
                    status.classList.remove('oculto');
                    // ver DELAY
                    setTimeout(function () {
                        console.log("Delay ERROR de 4 segundos.");
                        status.classList.add('oculto');
                    }, 2000);

                    throw new Error('Error al enviar los datos');

                }
            })
            .then(dataResponse => {
                let html = "<h3> Datos agregados: </h3><br>"
                html += generarHTML(dataResponse);
                contenedorRespuesta.innerHTML = html;

            })
            .catch(error => {
                console.error('Error catch :', error);
                contenedorRespuesta.innerHTML = "<div>ERROR FETCH (catch)</div>";
            });
    }


    function listar(url, id) {

        fetch(url + id)
            .then(response => {
                if (response.ok) {
                    console.log('Datos recibidos correctamente');
                    return response.json(); // Convertir la respuesta a JSON
                } else {
                    console.log("ERROR else" + response.status);
                    alert("ERROR " + response.status);
                }
            })
            .then(data => {
                // Procesar los datos y generar contenido HTML dinámicamente
                const html = generarHTML(data);
                // Agregar el contenido HTML al elemento deseado en el DOM
                console.log("Se debe actualizar HTML con el resultado")
                contenedorRespuesta.innerHTML = html;

            })
            .catch(error => {
                console.error('Error catch :', error);
                contenedorRespuesta.innerHTML = "<div>ERROR FETCH (catch)</div>";

            });
    }

    // Función para generar contenido HTML dinámicamente a partir de los datos
    function generarHTML(data) {
        let html = '';

        if (Array.isArray(data)) { // si es un array de objetos puede procesar cada uno

            data.forEach(obj => {
                for (let key in obj) {
                    console.log(obj[key]);
                    if (typeof obj[key] === "object") { // Utilizar typeof para verificar el tipo de objeto
                        const objeto = obj[key];
                        for (let key2 in objeto) {
                            html += `<div>${key2}: ${objeto[key2]}</div>`;
                        }
                    } else {
                        html += `<div>${key}: ${obj[key]}</div>`;
                    }
                }
            });
        } else {

            for (let key in data) {  // si en cambio llega solo un objeto, también puede procesarlo

                if (typeof data[key] === "object") {
                    const objeto = data[key];
                    for (let key2 in objeto) {
                        html += `<div>${key2}: ${objeto[key2]}</div>`;
                    }
                } else {
                    html += `<div>${key}: ${data[key]}</div>`;
                }
            }

        }

        return html;
    }


    // FUNCIONES ESPECIFICAS ligadas a los botones.

    function agregarPaciente() {
        // Obtener los valores de los campos de entrada
        let paciente = {
            nombre: nombreInput.value,
            apellido: apellidoInput.value,
            dni: dniInput.value,
            fechaAlta: fechaAltaInput.value,
            domicilio: {
                calle: calleInput.value,
                numero: numeroInput.value,
                ciudad: ciudadInput.value,
                departamento: departamentoInput.value
            }
        }

        // Enviar solicitud POST al handler de agregar paciente
        enviarDatos('http://localhost:8080/pacientes/agregar', paciente);

        formPacientes.reset();
    }

    function listarPacientes() {
        listar("http://localhost:8080/pacientes", "");
    }

    function buscarPaciente() {

        let id = idP.value;
        listar("http://localhost:8080/pacientes/", id);
    }

    function agregarOdontologo() {

        let odontologo = {
            nombre: nombreOInput.value,
            apellido: apellidoOInput.value,
            matricula: matriculaInput.value
        }

        enviarDatos('http://localhost:8080/odontologos/agregar', odontologo);

        formOdontologos.reset();

    }

    function listarOdontologos() {
        listar("http://localhost:8080/odontologos", "");
    }

    function buscarOdontologo() {

        let id = idO.value;
        listar("http://localhost:8080/odontologos/", id);
    }

    function agregarTurno() {

        let turno = {
            paciente: Number(pacienteIdInput.value),
            odontologo: Number(odontologoIdInput.value),
            fechaTurno: fechaTurnoInput.value
        }

        enviarDatos('http://localhost:8080/turnos/nuevo', turno);

        formTurnos.reset();

    }

    function listarTurnos() {
        listar("http://localhost:8080/turnos", "");
    }

    function buscarTurno() {

        let id = idT.value;
        listar("http://localhost:8080/turnos/", id);
    }


    /// cierre
});
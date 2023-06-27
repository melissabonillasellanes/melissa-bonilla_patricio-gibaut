document.addEventListener('DOMContentLoaded', function() {
    let agregarPaciente = document.getElementById('agregarP');
    console.log("")
    agregarPaciente.addEventListener('submit', function(event) {
      event.preventDefault();
  
      let pacienteData = {
        nombre: document.getElementById('nombre').value,
        apellido: document.getElementById('apellido').value,
        dni: document.getElementById('dni').value,
        fechaAlta: document.getElementById('fechaAlta').value,
        domicilio: {
                calle: document.getElementById('calle').value,
                numero: document.getElementById('numero').value,
                ciudad: document.getElementById('ciudad').value,
        }

      };

      let jsonData = JSON.stringify(pacienteData);

      console.log('Datos del Body:', pacienteData);
  
      fetch('/pacientes/agregar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: jsonData
      })
        .then(function(response) {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Error agregando paciente');
          }
        })
        .then(function(data) {
          // Handle the response from the server
          console.log('Paciente agregado correctamente:', data);
          // You can perform additional actions here if needed
        })
        .catch(function(error) {
          // Handle any errors that occur during the fetch request
          console.error('Error agregando Paciente:', error);
        });
    });
  });
  
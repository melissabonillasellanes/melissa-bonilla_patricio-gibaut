document.addEventListener('DOMContentLoaded', function() {
    var odontologoForm = document.getElementById('odontologoForm');
    
    odontologoForm.addEventListener('submit', function(event) {
      event.preventDefault();
  
      var odontologoData = {
        nombre: document.getElementById('nombre').value,
        apellido: document.getElementById('apellido').value,
        matricula: document.getElementById('matricula').value
      };
  
      var jsonData = JSON.stringify(odontologoData);
  
      fetch('http://localhost:8080/odontologos/agregar', {
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
            throw new Error('Error adding Odontologo');
          }
        })
        .then(function(data) {
          // Handle the response from the server
          console.log('Odontologo agregado correctamente:', data);
          // You can perform additional actions here if needed
        })
        .catch(function(error) {
          // Handle any errors that occur during the fetch request
          console.error('Error agregando Odontologo:', error);
        });
    });
  });
  
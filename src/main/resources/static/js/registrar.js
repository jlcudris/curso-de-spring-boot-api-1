// Call the dataTables jQuery plugin
$(document).ready(function() {


});

async function registrarUsuario(){
      let datos ={};
      datos.nombre = document.getElementById('nombre').value;
      datos.apellido = document.getElementById('apellido').value;
      datos.email = document.getElementById('email').value;
      datos.password = document.getElementById('password').value;

      let RepetirPass =document.getElementById('repetirPassword').value;

      if(RepetirPass != datos.password){
           alert('las contraseñas son diferentes');
           return
      }

      const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      alert('la cuenta fue creada con exito');
        window.location.href='login.html';
  }

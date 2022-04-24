// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();

   actualizarEmailDelusuario();

});

function actualizarEmailDelusuario(){

document.getElementById('txt-email-user').outerHTML=localStorage.email;

}

async function cargarUsuarios(){

      const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: getHeaders()

      });
      const usuarios = await request.json();

        let listadoHtml='';
        for(let usuario of usuarios){
            let telefono =usuario.telefono == null ? '-' : usuario.telefono;
            let btnEliminar ='<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
            let usuarioHTMl ='<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+telefono+'</td><td>'+ btnEliminar + '</td></tr>';
            listadoHtml +=usuarioHTMl;
        }
       document.querySelector('#usuarios tbody').outerHTML =listadoHtml;
  }
async function eliminarUsuario(id){
         const request = await fetch('api/usuarios/' + id, {
                method: 'DELETE',
                headers:getHeaders()
              });
              location.reload();
  }

  function getHeaders(){
    return  {
             'Accept': 'application/json',
             'Content-Type': 'application/json',
             'Authorization': localStorage.token
            };
  }




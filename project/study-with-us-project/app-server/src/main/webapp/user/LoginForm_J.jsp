<!DOCTYPE html>

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    
    </head>
    
    <style>
        .container {
  padding: 2rem 0rem;
}

@media (min-width: 576px){
  .modal-dialog {
    max-width: 400px;
    
    .modal-content {
      padding: 1rem;
    }
  }
}

.modal-header {
  .close {
    margin-top: -1.5rem;
  }
}

.form-title {
  margin: -2rem 0rem 2rem;
}

.btn-round {
  border-radius: 3rem;
}

.delimiter {
  padding: 1rem;  
}

.social-buttons {
  .btn {
    margin: 0 0.5rem 1rem;
  }
}

.signup-section {
  padding: 0.3rem 0rem;
}
    </style>
    
    
    
    
    <body>
    <div class="container">
    <button type="button" class="btn btn-info btn-round" data-toggle="modal" data-target="#loginModal">
      Login
    </button>
  </div>
  
  <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header border-bottom-0">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="form-title text-center">
            <h4>Login</h4>
          </div>
          <div class="d-flex flex-column text-center">
            <form>
              <div class="form-group">
                <input type="email" class="form-control" id="email1" placeholder="Your email address...">
              </div>
              <div class="form-group">
                <input type="password" class="form-control" id="password1" placeholder="Your password...">
              </div>
              <button type="button" class="btn btn-info btn-block btn-round">Login</button>
            </form>
  
            <div class="text-center text-muted delimiter">or use a social network</div>
            <div class="d-flex justify-content-center social-buttons">
              <button type="button" class="btn btn-secondary btn-round" data-toggle="tooltip" data-placement="top" title="Twitter">
                <i class="fab fa-twitter"></i>
              </button>
              <button type="button" class="btn btn-secondary btn-round" data-toggle="tooltip" data-placement="top" title="Facebook">
                <i class="fab fa-facebook"></i>
              </button>
              <button type="button" class="btn btn-secondary btn-round" data-toggle="tooltip" data-placement="top" title="Linkedin">
                <i class="fab fa-linkedin"></i>
              </button>
              </di>
            </div>
          </div>
        </div>
        <div class="modal-footer d-flex justify-content-center">
          <div class="signup-section">Not a member yet? <a href="#a" class="text-info"> Sign Up</a>.</div>
        </div>
      </div>
    </div>


    
        <script type="module">
  import { Toast } from 'bootstrap.esm.min.js'

  Array.from(document.querySelectorAll('.toast'))
    .forEach(toastNode => new Toast(toastNode))

        $(document).ready(function() {             $('#loginModal').modal('show');
  $(function () {
    $('[data-toggle="tooltip"]').tooltip()
  })
});
    </script>


</body>
</html>
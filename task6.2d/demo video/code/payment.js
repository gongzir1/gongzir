$(function() {
    $('form.require-validation').bind('submit', function(e) {
      var $form         = $(e.target).closest('form'),
          inputSelector = ['input[type=email]', 'input[type=password]',
                           'input[type=text]', 'input[type=file]',
                           'textarea'].join(', '),
          $inputs       = $form.find('.required').find(inputSelector),
          $errorMessage = $form.find('div.error'),
          valid         = true;
  
      $errorMessage.addClass('hide');
      $('.has-error').removeClass('has-error');
      $inputs.each(function(i, el) {
        var $input = $(el);
        if ($input.val() === '') {
          $input.parent().addClass('has-error');
          $errorMessage.removeClass('hide');
          e.preventDefault(); // cancel on first error
        }
      });
    });
  });
  
  $(function() {
    var $form = $("#payment-form");
  
    $form.on('submit', function(e) {
      if (!$form.data('cc-on-file')) {
        e.preventDefault();
        Stripe.setPublishableKey($form.data('stripe-publishable-key'));
        Stripe.createToken({
          number: $('.card-number').val(),
          cvc: $('.card-cvc').val(),
          exp_month: $('.card-expiry-month').val(),
          exp_year: $('.card-expiry-year').val()
        }, stripeResponseHandler);
      }
    });
  
    function stripeResponseHandler(status, response) {
      if (response.error) {
        $('.error')
          .removeClass('hide')
          .find('.alert')
          .text(response.error.message);
      } else {
        // token contains id, last4, and card type
        var token = response['id'];
        // insert the token into the form so it gets submitted to the server
        $form.find('input[type=text]').empty();
        $form.append("<input type='hidden' name='reservation[stripe_token]' value='" + token + "'/>");
        $form.get(0).submit();
      }
    }
  })
  
  
  
          $(document).ready(function() {
  
              function loading() {
                  $('.paypalResult').show().html('<br><div class="alert alert-info">Please wait while we redirect you to PayPal to finish the checkout.</div>');
              }
  
              function formResult(data) {
                  $('.paypalResult').show().html('<br><div class="alert alert-success">Success: if you aren\'t redirected, hit the PayPal button.</div><meta http-equiv="refresh" content="2; url=https://paypal.ca">');
                  $('#paypalForm input').val('');
              }
  
              function onSubmit() {
                  $('#paypalForm').submit(function() {
                      var action = $(this).attr('action');
                      loading();
                      $.ajax({
                          url: action,
                          type: 'POST',
                          data: {
                              token: $('#token').val(),
                              action: $('#action').val()
                          },
                          success: function(data) {
                              formResult(data);
                          },
                          error: function(data) {
                              formResult(data);
                          }
                      });
                      return false;
                  });
              }
              onSubmit();
  
          });
  
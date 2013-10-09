$(document).ready(function () {
  $('#loader').removeClass('visibleoff').addClass('visibleon');
  $.ajax({url: "html/login.html", cache: false}).done(
    function( html ) {
      $('#login').html(html);
      $('#loader').removeClass('visibleon').addClass('visibleoff');
    });

});
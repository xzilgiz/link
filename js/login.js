$(document).ready(function () {
  $('#loader').removeClass('visibleoff').addClass('visibleon');
  $.ajax({url: "template/body.tmpl", cache: false}).done(
    function( html ) {
      $('#body').append(html);
      $('#loader').removeClass('visibleon').addClass('visibleoff');
    });

});
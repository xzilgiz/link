$(document).ready(function () {
  $('#loader').removeClass('visibleoff').addClass('visibleon');
  $.ajax({url: "html/body.html", cache: false}).done(
    function( html ) {
      $('#body').append(html);
      $('#loader').removeClass('visibleon').addClass('visibleoff');
      
      
      var h = Math.max($(window).height(), $(document).height()) - ($('#header').height() + $('#footer').height());
      
      $('#content_left').height(h);
      $('#content_right').height(h);
      $('#content_center').height(h);
    });

});

$(window).resize(function() {
  var h = Math.max($(window).height(), $(document).height()) - ($('#header').height() + $('#footer').height());
      
      $('#content_left').height(h);
      $('#content_right').height(h);
      $('#content_center').height(h);
      
});
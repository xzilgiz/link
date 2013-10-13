$(document).ready(function() {
  $('#loader').removeClass('visibleoff').addClass('visibleon');
  $.ajax({url: "html/classparam.html", cache: false}).done(
    function( html ) {
      $('#center').html(html);
      $('#loader').removeClass('visibleon').addClass('visibleoff');

      //Draw select
      ////#entityID
      $.ajax({url: "EntityTypeView?action=select", cache: false}).done(function(html) {$("#entityID").html(html);});
      ////#classID
      $.ajax({url: "ClassTypeView?action=select", cache: false}).done(function(html) {$("#classID").html(html);});

      function drawtable() {
	    $(function() {
        $.ajax({
          url: "ClassParamView?action=view",
          dataType: "json"
        }).done(function(data) {
          console.log(data);
	      $(".slimtable-paging-div").remove();
	      $(".slimtable-sprites").remove();
	      $(".slimtable-sortboth").remove();
	    
	      $("#exampletable").slimtable({
            tableData: data,
            itemsPerPage: 30
          });
        }).fail(function(param1,param2) {
          console.log("error: "+param2);
        });
      });
	  }

      drawtable();

      $('#exampletable tbody').on('click','tr', function () {
        var nTds = $('td', this);

        $('#code').val($(nTds[0]).text());
        $('#num').val($(nTds[1]).text());
        $('#classID').val($(nTds[2]).text());
        $('#entityID').val($(nTds[3]).text());
      });

	  //////////////// Event Operation -->
      $('#ins').click(function(event) {
        var js_action       ='ins';
        var js_code         =$('#code').val();
        var js_num          =$('#num').val();
        var js_classID      =$('#classID').val();
        var js_entityID     =$('#entityID').val();

        $.get('ClassParamView',
          {action:js_action,code:js_code,num:js_num,classID:js_classID,entityID:js_entityID},function(responseText) { 
          drawtable();
        });
      });

      $('#upd').click(function(event) {
        var js_action       ='upd';
        var js_code         =$('#code').val();
        var js_num          =$('#num').val();
        var js_classID      =$('#classID').val();
        var js_entityID     =$('#entityID').val();
      
        $.get('ClassParamView',
           {action:js_action,code:js_code,num:js_num,classID:js_classID,entityID:js_entityID},function(responseText) { 

          drawtable();
        });
      });

      $('#delete').click(function(event) {
        var js_action       ='del';
        var js_code         =$('#code').val();
     
        $.get('ClassParamView',{action:js_action,code:js_code},function(responseText) {
          drawtable();
        });
      });
  });
});
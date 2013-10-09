			$(document).ready(function() {
			  $('#loader').removeClass('visibleoff').addClass('visibleon');
              $.ajax({url: "html/entitytype.html", cache: false}).done(
                function( html ) {
                  $('#tableui').html(html);
                  $('#tableui').width($('#center').width() - 20);
                  $('#loader').removeClass('visibleon').addClass('visibleoff');


                
                
                
                var oTable = $('#example').dataTable( {
				  "sAjaxSource": "EntityTypeView?action=view",
				  "bProcessing": true, //Splash
				  "bPaginate": false,  //prev next
                  "bFilter": true,     //Search
                  "bSort": true,       //Sort
                  "bInfo": false       //Info
				} );
				//////////////// Operation -->
                
                $('#example tbody tr').live('click', function () {
                    var nTds = $('td', this);

                    $('#code').val($(nTds[0]).text());
                    $('#sense').val($(nTds[1]).text());
                    $('#required option:value').val($(nTds[2]).text());
                    //if ($(nTds[2]).text() != 'Y') {
                    //  $("#required [value='N']").attr("selected", "selected");
                    //} else {
                    //  $("#required [value='Y']").attr("selected", "selected");
                    //}
                } );



                ///////////////////
                $('#ins').click(function(event) {
                    var js_action='ins';
                    var js_code=$('#code').val();
                    var js_sense=$('#sense').val();
                    var js_required=$('#required').val();
                  $.get('EntityTypeView',{action:js_action,code:js_code,sense:js_sense,required:js_required},function(responseText) { 

                    var oTable = $('#example').dataTable( {
				      "bDestroy": true,
				      "sAjaxSource": "EntityTypeView?action=view",
  				      "bProcessing": true, //Splash
				      "bPaginate": false,  //prev next
                      "bFilter": true,     //Search
                      "bSort": true,       //Sort
                      "bInfo": false       //Info
				    } );
				    //oTable.fnDraw();

                  });
                });

                $('#upd').click(function(event) {
                    var js_action='upd';
                    var js_code=$('#code').val();
                    var js_sense=$('#sense').val();
                    var js_required=$('#required').val();
                  $.get('EntityTypeView',{action:js_action,code:js_code,sense:js_sense,required:js_required},function(responseText) { 

                    var oTable = $('#example').dataTable( {
				      "bDestroy": true,
				      "sAjaxSource": "EntityTypeView?action=view",
  				      "bProcessing": true, //Splash
				      "bPaginate": false,  //prev next
                      "bFilter": true,     //Search
                      "bSort": true,       //Sort
                      "bInfo": false       //Info
				    } );
				    //oTable.fnDraw();
				    
                  });
                });
            
                $('#delete').click(function(event) {
                    var js_action='del';
                    var js_code=$('#code').val();
                    var js_sense=$('#sense').val();
                    var js_required=$('#required').val();
                 $.get('EntityTypeView',{action:js_action,code:js_code,sense:js_sense,required:js_required},function(responseText) { 
                    $('#code').val('');
                    $('#sense').val('');
                    $('#required').val('');


                    var oTable = $('#example').dataTable( {
				      "bDestroy": true,
				      "sAjaxSource": "EntityTypeView?action=view",
  				      "bProcessing": true, //Splash
				      "bPaginate": false,  //prev next
                      "bFilter": true,     //Search
                      "bSort": true,       //Sort
                      "bInfo": false       //Info
				    } );

                  });
                });
              });
			});
console.log("this is home url")

const togglesidebar=()=>{

 if ($('.sidebar').is(":visible")){
 //band karna hai
      $(".sidebar").css("display","none");
       $(".content").css("margin-left","0%");
 }else{
 //show karana hai
       $(".sidebar").css("display","block");
       $(".content").css("margin-left","20%");
 }
}
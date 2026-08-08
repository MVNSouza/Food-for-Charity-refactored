var matrix = [[0,0,0,1,0,0,0,0,1,0,0,1,0,0,1,0,0,1,1,0,0,0,0,0,4,4,0,0,0,0,1,0,1,0,0,1,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0],[0,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,1,1,0,0,0,1,1,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0]]
var packages = [{
"name": " com.foodforcharity.app.web.controller", "color": " #3182bd"
}
,{
"name": " com.foodforcharity.app.usecase.profile.modifymenuitem", "color": " #6baed6"
}
,{
"name": " com.foodforcharity.app.usecase.reviews.viewcomplaint", "color": " #9ecae1"
}
,{
"name": " com.foodforcharity.app.usecase.account.changepassword", "color": " #c6dbef"
}
,{
"name": " com.foodforcharity.app.usecase.foodreservation.resetquantityrequested", "color": " #e6550d"
}
,{
"name": " com.foodforcharity.app.usecase.foodreservation.requestcompletion", "color": " #fd8d3c"
}
,{
"name": " com.foodforcharity.app.domain.entity", "color": " #fdae6b"
}
,{
"name": " com.foodforcharity.app.infrastructure.repository", "color": " #fdd0a2"
}
,{
"name": " com.foodforcharity.app.web.security", "color": " #31a354"
}
,{
"name": " com.foodforcharity.app.usecase.profile.addmenu", "color": " #74c476"
}
,{
"name": " com.foodforcharity.app.web.configration", "color": " #a1d99b"
}
,{
"name": " com.foodforcharity.app.domain.response", "color": " #c7e9c0"
}
,{
"name": " com.foodforcharity.app.web.validator", "color": " #756bb1"
}
,{
"name": " com.foodforcharity.app.usecase.reviews.raterequest", "color": " #9e9ac8"
}
,{
"name": " com.foodforcharity.app.usecase.account.donorregisteration", "color": " #bcbddc"
}
,{
"name": " com.foodforcharity.app.usecase.foodreservation.createrequest", "color": " #dadaeb"
}
,{
"name": " com.foodforcharity.app.domain.service", "color": " #636363"
}
,{
"name": " com.foodforcharity.app.usecase.account.doneeregisteration", "color": " #969696"
}
,{
"name": " com.foodforcharity.app.usecase.account.changestatus", "color": " #bdbdbd"
}
,{
"name": " com.foodforcharity.app.domain.constant", "color": " #d9d9d9"
}
,{
"name": " com.foodforcharity.app.infrastructure.security", "color": " #3182bd"
}
,{
"name": " com.foodforcharity.app.web", "color": " #6baed6"
}
,{
"name": " com.foodforcharity.app.domain.convertor", "color": " #9ecae1"
}
,{
"name": " com.foodforcharity.app.usecase.reviews.filecomplaint", "color": " #c6dbef"
}
,{
"name": " com.foodforcharity.app.mediator", "color": " #e6550d"
}
,{
"name": " com.foodforcharity.app.web.model", "color": " #fd8d3c"
}
,{
"name": " com.foodforcharity.app.usecase.profile.selectpreferences", "color": " #fdae6b"
}
,{
"name": " com.foodforcharity.app.usecase.profile.getmenuitem", "color": " #fdd0a2"
}
,{
"name": " com.foodforcharity.app.usecase.account.getdonor", "color": " #31a354"
}
,{
"name": " com.foodforcharity.app.web.filter", "color": " #74c476"
}
,{
"name": " com.foodforcharity.app.web.service", "color": " #a1d99b"
}
,{
"name": " com.foodforcharity.app.usecase.reviews.viewcomplaints", "color": " #c7e9c0"
}
,{
"name": " com.foodforcharity.app.web.dto", "color": " #756bb1"
}
,{
"name": " com.foodforcharity.app.usecase.profile.deletemenuitem", "color": " #9e9ac8"
}
,{
"name": " com.foodforcharity.app.usecase.account.getdonee", "color": " #bcbddc"
}
,{
"name": " com.foodforcharity.app.domain.security", "color": " #dadaeb"
}
,{
"name": " com.foodforcharity.app.usecase.reviews.modifycomplaintstatus", "color": " #636363"
}
];

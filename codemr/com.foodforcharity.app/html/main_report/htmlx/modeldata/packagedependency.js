var matrix = [[0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,3,3,0,0,0,0,0,1,0,1,0,0,1,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[4,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,1,0,0,0,1,0,0,0,0,4,3,1,0,0,0,0,0,0,1,0,1,0,0],[0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[4,0,0,1,0,0,0,0,0,0,0,0,0,3,0,0,1,0,0,1,0,0,0,0,0,0,4,3,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0],[0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0]]
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
"name": " com.foodforcharity.app.domain.valueobject", "color": " #fdd0a2"
}
,{
"name": " com.foodforcharity.app.infrastructure.repository", "color": " #31a354"
}
,{
"name": " com.foodforcharity.app.web.security", "color": " #74c476"
}
,{
"name": " com.foodforcharity.app.usecase.profile.addmenu", "color": " #a1d99b"
}
,{
"name": " com.foodforcharity.app.web.controller.donee", "color": " #c7e9c0"
}
,{
"name": " com.foodforcharity.app.web.configration", "color": " #756bb1"
}
,{
"name": " com.foodforcharity.app.domain.response", "color": " #9e9ac8"
}
,{
"name": " com.foodforcharity.app.web.validator", "color": " #bcbddc"
}
,{
"name": " com.foodforcharity.app.usecase.reviews.raterequest", "color": " #dadaeb"
}
,{
"name": " com.foodforcharity.app.usecase.account.donorregisteration", "color": " #636363"
}
,{
"name": " com.foodforcharity.app.usecase.foodreservation.createrequest", "color": " #969696"
}
,{
"name": " com.foodforcharity.app.domain.service", "color": " #bdbdbd"
}
,{
"name": " com.foodforcharity.app.usecase.account.doneeregisteration", "color": " #d9d9d9"
}
,{
"name": " com.foodforcharity.app.usecase.account.changestatus", "color": " #3182bd"
}
,{
"name": " com.foodforcharity.app.domain.constant", "color": " #6baed6"
}
,{
"name": " com.foodforcharity.app.infrastructure.security", "color": " #9ecae1"
}
,{
"name": " com.foodforcharity.app.web", "color": " #c6dbef"
}
,{
"name": " com.foodforcharity.app.domain.convertor", "color": " #e6550d"
}
,{
"name": " com.foodforcharity.app.usecase.reviews.filecomplaint", "color": " #fd8d3c"
}
,{
"name": " com.foodforcharity.app.mediator", "color": " #fdae6b"
}
,{
"name": " com.foodforcharity.app.web.model", "color": " #fdd0a2"
}
,{
"name": " com.foodforcharity.app.usecase.profile.selectpreferences", "color": " #31a354"
}
,{
"name": " com.foodforcharity.app.usecase.profile.getmenuitem", "color": " #74c476"
}
,{
"name": " com.foodforcharity.app.usecase.account.getdonor", "color": " #a1d99b"
}
,{
"name": " com.foodforcharity.app.web.controller.person", "color": " #c7e9c0"
}
,{
"name": " com.foodforcharity.app.web.filter", "color": " #756bb1"
}
,{
"name": " com.foodforcharity.app.web.service", "color": " #9e9ac8"
}
,{
"name": " com.foodforcharity.app.usecase.reviews.viewcomplaints", "color": " #bcbddc"
}
,{
"name": " com.foodforcharity.app.web.dto", "color": " #dadaeb"
}
,{
"name": " com.foodforcharity.app.usecase.profile.deletemenuitem", "color": " #636363"
}
,{
"name": " com.foodforcharity.app.usecase.account.getdonee", "color": " #969696"
}
,{
"name": " com.foodforcharity.app.domain.security", "color": " #bdbdbd"
}
,{
"name": " com.foodforcharity.app.usecase.reviews.modifycomplaintstatus", "color": " #d9d9d9"
}
];

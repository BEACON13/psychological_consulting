webpackJsonp([6],{"4B8t":function(e,t){},vdVF:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=o("P9l9"),r={name:"login",data:function(){return{form:{username:"",type:"",pwd:""}}},methods:{Login:function(){var e=this;""==this.form.type?this.$message.error("请选择用户类型！！"):"学生"===this.form.type?Object(a._7)(this.form).then(function(t){t.status?(localStorage.setItem("pid",5),localStorage.setItem("roleName","学生"),e.$store.commit("user/isStudent",t.data),e.$router.replace({path:"main/student/index"})):e.$message.error("账号或密码错误！")}).catch(function(){console.log("fail!")}):Object(a._6)(this.form).then(function(t){t.status?("中心管理员"==e.form.type?(localStorage.setItem("pid",1),localStorage.setItem("roleName","中心管理员"),e.$router.replace({path:"/main"})):"初访员"==e.form.type?(localStorage.setItem("pid",2),localStorage.setItem("roleName","初访员"),e.$router.push({name:"firstVisitReport"})):"心理助理"==e.form.type?(localStorage.setItem("pid",3),localStorage.setItem("roleName","心理助理"),e.$router.push({name:"allApply"})):"咨询师"==e.form.type&&(localStorage.setItem("pid",4),localStorage.setItem("roleName","咨询师"),e.$router.push({name:"consultReport"})),e.$store.commit("user/isPerson",t.data)):e.$message.error("账号或密码错误！")}).catch(function(){console.log("fail!")})},RoleChange:function(){console.log(this.form.type)}}},l={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("transition",{attrs:{name:"el-zoom-in-top"}},[o("div",{staticClass:"loginForm"},[o("el-form",{ref:"loginForm",staticClass:"form",attrs:{model:e.form,"label-width":"80px"}},[o("el-form-item",{staticStyle:{"margin-top":"60px"},attrs:{label:"账号"}},[o("el-input",{staticStyle:{width:"90%"},attrs:{clearable:""},model:{value:e.form.username,callback:function(t){e.$set(e.form,"username",t)},expression:"form.username"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"密码"}},[o("el-input",{staticStyle:{width:"90%"},attrs:{clearable:"","show-password":""},model:{value:e.form.pwd,callback:function(t){e.$set(e.form,"pwd",t)},expression:"form.pwd"}})],1),e._v(" "),o("el-form-item",{staticStyle:{"margin-bottom":"30px",width:"100%"},attrs:{label:"角色"}},[o("el-radio-group",{on:{change:e.RoleChange},model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},[o("el-radio",{attrs:{label:"学生"}}),e._v(" "),o("el-radio",{attrs:{label:"中心管理员"}}),e._v(" "),o("el-radio",{attrs:{label:"初访员"}}),e._v(" "),o("el-radio",{attrs:{label:"心理助理"}}),e._v(" "),o("el-radio",{attrs:{label:"咨询师"}})],1)],1),e._v(" "),o("el-form-item",[o("el-button",{attrs:{type:"primary"},on:{click:e.Login}},[e._v("登录")]),e._v(" "),o("el-button",[e._v("取消")])],1)],1)],1)])},staticRenderFns:[]};var s=o("VU/8")(r,l,!1,function(e){o("4B8t")},"data-v-b781421c",null);t.default=s.exports}});
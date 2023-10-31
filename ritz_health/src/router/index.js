import {createRouter, createWebHashHistory} from 'vue-router'
//import checkItem from '../views/checkItem.html'
const routes=[
    //{path:"/",name:"index",component: ()=>import('../views/main')},
    //{path:"/",name:"index",alias: checkItem},
    //{path:"/checkItem",name:"checkItem",component: ()=>import('../views/checkItem')},
    //{path:"/checkItem",name:"checkItem",component: ()=>import('../views/checkItem')},
    //{path:"/",name:"index",component: ()=>import('./@/public/main.html')},
];
const router= createRouter({
    history:createWebHashHistory(),
    routes
})
export default router;

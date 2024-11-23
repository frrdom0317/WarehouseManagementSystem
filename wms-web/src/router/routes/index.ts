export const constantRoutes = [
    {
        path: '/',
        name: 'layout',
        component: () => import("@/components/Index.vue"),
        redirect: '/home',
        meta: {
            title: 'Layout',
            icon: 'HomeFilled',
            show: false
        },
        children: [
            {
                path: '/home',
                name: 'home',
                component: () => import('@/views/Home.vue'),
                meta: {
                    title: '首页',
                    icon: 'HomeFilled',
                    show: true
                }
            }
        ]
    },
    {
        path:'/login',
        name:'login',
        component: ()=> import('@/views/Login.vue'),
        meta: {
            title: 'Login',
            icon: 'HomeFilled',
            show: false
        },
    }
 ]
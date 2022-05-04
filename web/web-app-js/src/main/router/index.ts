import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const AdministratePage = () => import('@page/administrate/AdministratePage.vue')

const UserListPage = () => import('@page/administrate/UserListPage.vue')

const TenantListPage = () => import('@page/administrate/TenantListPage.vue')



const PbxPage = () => import( '@page/pbx/PbxPage.vue' )

const CallRecordPage = () => import('@page/pbx/CallRecordPage.vue')




const MainLayout = () => import( '@layout/MainLayout.vue' )
// const BlankLayout = () => import( '@layout/BlankLayout.vue' )
//
const LoginPage = () => import( '@page/LoginPage.vue' )
const RegistrationPage = () => import( '@page/RegistrationPage.vue' )

const routes: RouteRecordRaw[] = [
    {
        name: 'main',
        path: '/',
        component: MainLayout,
        children: [
            {
                name: 'pbx',
                path: 'pbx',
                component: PbxPage,
                children: [
                    {
                        name: 'call-record',
                        path: 'call-record',
                        component: CallRecordPage
                    }
                ]
            },
            {
                name: 'administrate',
                path: 'administrate',
                component: AdministratePage,
                children: [
                    {
                        name: 'user-list',
                        path: 'user-list',
                        component: UserListPage
                    },
                    {
                        name: 'tenant-list',
                        path: 'tenant-list',
                        component: TenantListPage
                    }
                ]
            }
        ]
    }
]

export default createRouter( {
    routes,
    history: createWebHashHistory()
} )

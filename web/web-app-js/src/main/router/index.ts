import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

import store from '@main/store'

import { AppGetter } from '@main/store/app/getters'

const AdministratePage = () => import( '@page/administrate/AdministratePage.vue' )

const UserListPage = () => import( '@page/administrate/UserListPage.vue' )

const TenantListPage = () => import( '@page/administrate/TenantListPage.vue' )

const SipSubscriberListPage = () => import( '@page/administrate/SipSubscriberListPage.vue' )

const PbxPage = () => import( '@page/pbx/PbxPage.vue' )

const CallRecordPage = () => import( '@page/pbx/CallRecordPage.vue' )

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
                redirect: '/pbx/call-record',
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
                beforeEnter: ( to, from, next ) => {
                    if ( !store.getters[ AppGetter.USER_IS_ROOT ] ) {
                        next( { name: 'main' } )
                    }
                    next()
                },
                redirect: '/administrate/user-list',
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
                    },
                    {
                        name: 'sip-subscriber-list',
                        path: 'sip-subscriber-list',
                        component: SipSubscriberListPage
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

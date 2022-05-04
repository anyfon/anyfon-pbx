import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const PbxPage = () => import( '@page/pbx/PbxPage.vue' )

const CallRecordPage = () => import('@page/pbx/CallRecordPage.vue')
//
// const CatalogProductPage = () => import( '@page/catalog/CatalogProductPage.vue' )
//
//
const MainLayout = () => import( '@layout/MainLayout.vue' )
// const BlankLayout = () => import( '@layout/BlankLayout.vue' )
//
// const LoginPage = () => import( '@page/LoginPage.vue' )
// const RegistrationPage = () => import( '@page/RegistrationPage.vue' )

const routes: RouteRecordRaw[] = [
    {
        name: 'main',
        path: '/',
        component: MainLayout,
        children: [
            //         {
            //             name: 'catalog',
            //             path: 'catalog',
            //             component: CatalogProductPage
            //         },
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
                    }
        ]
    }
]

export default createRouter( {
    routes,
    history: createWebHashHistory()
} )

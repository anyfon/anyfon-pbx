import quasarConfigurer from './quasar-configurer'
import { App } from 'vue'

import router from '@main/router'

import PanelSplitter from '@main/component/PanelSplitter.vue'
import store from '@main/store'

export default ( app: App ) => {
    app.config.unwrapInjectedRef = true
    app.use( store )
    app.use( router )
    quasarConfigurer( app )
    app.component( PanelSplitter.name, PanelSplitter )
}

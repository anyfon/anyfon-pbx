import quasarConfigurer from './quasar-configurer'
import { App } from 'vue'

import router from '@main/router'

import PanelSplitter from '@main/component/PanelSplitter.vue'

export default ( app: App ) => {
    app.config.unwrapInjectedRef = true
    app.use( router )
    quasarConfigurer( app )
    app.component( PanelSplitter.name, PanelSplitter )

}

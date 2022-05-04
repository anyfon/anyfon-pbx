import quasarConfigurer from './quasar-configurer'
import { App } from 'vue'

import router from '@main/router'

export default ( app: App ) => {
    app.config.unwrapInjectedRef = true
    app.use( router )
    quasarConfigurer( app )
}

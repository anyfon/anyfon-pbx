import '@style/main.sass'
import { createApp, defineComponent, h, resolveComponent } from 'vue'

import appConfigurer from '@main/config/app-configurer'

const App = defineComponent( {
    setup() {
        return () => h( resolveComponent( 'router-view' ) )
    }
} )

const app = createApp( App )

appConfigurer( app )

app.mount( '#desktop-web-app' )

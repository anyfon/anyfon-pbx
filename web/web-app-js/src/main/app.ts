import '@style/main.sass'
import { createApp, defineComponent, h, onMounted, resolveComponent } from 'vue'

import appConfigurer from '@main/config/app-configurer'
import { useStore } from 'vuex'
import { AppAction } from '@main/store/app/actions'

const App = defineComponent( {
    setup() {
        const store = useStore()

        store.dispatch( AppAction.FETCH_USER_SELF)

        return () => h( resolveComponent( 'router-view' ) )
    }
} )

const app = createApp( App )

appConfigurer( app )

app.mount( '#desktop-web-app' )

import installQuasar from 'quasar/src/install-quasar'
import { App } from 'vue'

import {
    QAvatar,
    QBtn,
    QIcon,
    QInput,
    QItem,
    QList,
    QResizeObserver,
    QSplitter,
    QSeparator,

    QTab,
    QTabs,
    QTable,

    LocalStorage,
    Ripple
} from 'quasar'

const components = {
    QAvatar,
    QBtn,
    QIcon,
    QInput,
    QItem,
    QList,
    QResizeObserver,
    QSplitter,
    QSeparator,
    QTab,
    QTabs,
    QTable
}

const plugins = {
    LocalStorage
}

const directives = {
    Ripple
}

export default ( app: App ) => {
    installQuasar( app, { components, directives, plugins } )
}

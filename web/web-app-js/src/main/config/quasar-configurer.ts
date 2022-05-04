import installQuasar from 'quasar/src/install-quasar'
import { App } from 'vue'

import {
    QAvatar,
    QBtn,
    QIcon,
    QInput,
    QItem,
    QItemLabel,
    QItemSection,
    QList,
    QResizeObserver,
    QRouteTab,
    QSplitter,
    QSeparator,

    QTab,
    QTabs,
    QTable,
    QToggle,

    LocalStorage,
    Ripple
} from 'quasar'

const components = {
    QAvatar,
    QBtn,
    QIcon,
    QInput,
    QItem,
    QItemLabel,
    QItemSection,
    QList,
    QResizeObserver,
    QRouteTab,
    QSplitter,
    QSeparator,
    QTab,
    QTabs,
    QTable,
    QToggle
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

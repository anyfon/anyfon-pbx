export default {
    data() {
        return {
            fullHeightSize: 0,
            fullWidthSize: 0,
            headerHeightSize: 0
        }
    },
    computed: {
        contentHeight() {
            return this.fullHeightSize - this.headerHeightSize
        },
        contentHeightPx() {
            return this.contentHeight + 'px'
        },
        contentStyle() {
            return {
                height: this.contentHeightPx
            }
        }
    },
    methods: {
        setFullSize( value: any ) {
            this.fullHeightSize = value.height || value
            this.fullWidthSize = value.width || value
        },
        setHeaderHeight( value: any ) {
            this.headerHeightSize = value.height || value
        }
    },
    provide() {
        return {
            setMainHeaderSize: this.setHeaderHeight
        }
    }
}

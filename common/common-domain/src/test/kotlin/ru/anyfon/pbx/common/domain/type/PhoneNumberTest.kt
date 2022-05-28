package ru.anyfon.pbx.common.domain.type

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class PhoneNumberTest {
    @Test
    fun `Creating an internal Phone number`() {

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.Extension("79261119792")  // External number
        }

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.Extension("not-number")
        }

        val internalNumberOfInt = PhoneNumber.Extension("000103162546")

        assertTrue(internalNumberOfInt.toString() == "000103162546")

        assertTrue(internalNumberOfInt.value == "000103162546")


        val internalNumberOfString = PhoneNumber.Extension("000-165452-205")

        assertTrue(internalNumberOfString.toString() == "000165452205")

        assertTrue(internalNumberOfString.value == "000165452205")
    }

    @Test
    fun `Creating an External phone number`() {

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.External("00025765234")  // Internal number
        }

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.External("not-number")
        }

        val externalNumberOfInt = PhoneNumber.External(79261119792, "External number")

        assertTrue(externalNumberOfInt.toString() == "79261119792 <External number>")

        assertTrue(externalNumberOfInt.name.toString() == "External number")

        assertTrue(externalNumberOfInt.number == "79261119792")

        assertTrue(externalNumberOfInt.value == "79261119792 <External number>")


        val externalNumberOfString = PhoneNumber.External("+7 (926) 111-97-92", "External number with trash")

        assertTrue(externalNumberOfString.toString() == "79261119792 <External number with trash>")

        assertTrue(externalNumberOfString.name.toString() == "External number with trash")

        assertTrue(externalNumberOfString.number == "79261119792")
        
        assertTrue(externalNumberOfString.value == "79261119792 <External number with trash>")
    }

    @Test
    fun `Creating an Any phone number`() {

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.Any("25")  // Short number
        }

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.Any("25234992929929299292992929")  // Long number
        }

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.Any("not-number")
        }

        val anyNumberOfInt = PhoneNumber.Any(79261119792)

        assertTrue(anyNumberOfInt.number == "79261119792")

        assertTrue(anyNumberOfInt.value == "79261119792")


        val anyNumberOfString = PhoneNumber.Any("+7 (926) 111-97-92")

        assertTrue(anyNumberOfString.number == "79261119792")

        assertTrue(anyNumberOfString.value == "79261119792")

    }

    @Test
    fun `Creating anonymous or Unavailable Any phone numbers`() {
        val anonymousAnyPhoneNumber = PhoneNumber.Any("anonymous")

        assertTrue(anonymousAnyPhoneNumber.toString() == "anonymous")

        assertTrue(anonymousAnyPhoneNumber.value == "anonymous")

        val unavailableAnyPhoneNumber = PhoneNumber.Any("Unavailable")

        assertTrue(unavailableAnyPhoneNumber.toString() == "Unavailable")

        assertTrue(unavailableAnyPhoneNumber.value == "Unavailable")

    }

    @Test
    fun `Creating anonymous or Unavailable External phone numbers`() {
        val anonymousExternalPhoneNumber = PhoneNumber.External("anonymous")

        assertTrue(anonymousExternalPhoneNumber.toString() == "anonymous")

        assertTrue(anonymousExternalPhoneNumber.value == "anonymous")

        val unavailableExternalPhoneNumber = PhoneNumber.External("Unavailable")

        assertTrue(unavailableExternalPhoneNumber.toString() == "Unavailable")

        assertTrue(unavailableExternalPhoneNumber.value == "Unavailable")
    }

}

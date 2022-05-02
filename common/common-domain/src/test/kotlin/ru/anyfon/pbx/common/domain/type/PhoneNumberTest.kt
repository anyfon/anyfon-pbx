package ru.anyfon.pbx.common.domain.type

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PhoneNumberTest {
    @Test
    fun `Creating an internal Phone number`() {

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.Internal("79261119792")  // External number
        }

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.Internal("not-number")
        }

        val internalNumberOfInt = PhoneNumber.Internal(4216)

        assertTrue(internalNumberOfInt.toString() == "4216")

        assertTrue(internalNumberOfInt.value == "4216")


        val internalNumberOfString = PhoneNumber.Internal("5-(21)-63")

        assertTrue(internalNumberOfString.toString() == "52163")

        assertTrue(internalNumberOfString.value == "52163")
    }

    @Test
    fun `Creating an External phone number`() {

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.External("2576")  // Internal number
        }

        assertThrows(IllegalArgumentException::class.java) {
            PhoneNumber.External("not-number")
        }

        val externalNumberOfInt = PhoneNumber.External(79261119792)

        assertTrue(externalNumberOfInt.toString() == "79261119792")

        assertTrue(externalNumberOfInt.value == "79261119792")


        val externalNumberOfString = PhoneNumber.External("+7 (926) 111-97-92")

        assertTrue(externalNumberOfString.toString() == "79261119792")

        assertTrue(externalNumberOfString.value == "79261119792")
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

        assertTrue(anyNumberOfInt.toString() == "79261119792")

        assertTrue(anyNumberOfInt.value == "79261119792")


        val anyNumberOfString = PhoneNumber.Any("+7 (926) 111-97-92")

        assertTrue(anyNumberOfString.toString() == "79261119792")

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
    fun `Creating anonymous or Unavailable Internal phone numbers`() {
        val anonymousInternalPhoneNumber = PhoneNumber.Internal("anonymous")

        assertTrue(anonymousInternalPhoneNumber.toString() == "anonymous")

        assertTrue(anonymousInternalPhoneNumber.value == "anonymous")

        val unavailableInternalPhoneNumber = PhoneNumber.Internal("Unavailable")

        assertTrue(unavailableInternalPhoneNumber.toString() == "Unavailable")

        assertTrue(unavailableInternalPhoneNumber.value == "Unavailable")
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

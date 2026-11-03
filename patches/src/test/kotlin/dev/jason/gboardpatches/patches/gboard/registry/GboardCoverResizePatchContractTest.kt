package dev.jason.gboardpatches.patches.gboard.registry

import dev.jason.gboardpatches.patches.gboard.shared.generated.GboardTargetAdmission
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GboardCoverResizePatchContractTest {
    @Test
    fun nativeResizeIsOffByDefaultAndNotAdvertisedForUnverified18_5_4() {
        val registrations = GboardPublishedPatchCatalog.morpheRegistrations
        val resize = registrations.singleOrNull { it.name == "Independent cover/main Resize profiles" }
        val eligible1803 =
            GboardTargetAdmission.versionNames == listOf("18.0.3.954559732-release-arm64-v8a") &&
                GboardTargetAdmission.admittedContributions("package_rename").isNotEmpty()
        if (eligible1803) {
            assertNotNull(resize)
            assertFalse(resize!!.use)
        } else {
            assertNull("Unverified native selectors must not be advertised for 18.5.4", resize)
        }
        assertTrue(registrations.any { it.name == "Package Rename" })
    }
}

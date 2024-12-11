import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.omidpaytask.data.remote.RemoteProductListRepositoryImpl
import com.example.omidpaytask.data.remote.dto.ProductDto
import com.example.omidpaytask.domain.repository.RemoteProductListRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class RemoteProductListRepositoryTest {

    private val mockPager = mockk<Pager<Int, ProductDto>>(relaxed = true)
    private val repository = RemoteProductListRepositoryImpl(mockPager)

    // تست بازگشت صحیح Pager
    @Test
    fun `getProducts should return a Pager object`() {
        val result = repository.getProducts()
        assertNotNull(result)
    }

    // تست مقداردهی به Pager با پیکربندی صحیح
    @Test
    fun `getProducts should return Pager with correct page size`() {
        val pagingConfig = PagingConfig(pageSize = 10)
        val pager = Pager(config = pagingConfig) {
            mockk<ProductPagingSource>()
        }
        every { mockPager.load(any()) } returns pager

        val result = repository.getProducts()
        assertEquals(10, result.config.pageSize)
    }

    // تست رفتار در صورت خطا در API یا مشکلات شبکه
    @Test
    fun `getProducts should handle errors gracefully when API call fails`() {
        val exception = Exception("Network Error")
        every { mockPager.load(any()) } throws exception

        assertThrows(Exception::class.java) {
            repository.getProducts()
        }
    }

    // تست رفتار زمانی که داده‌ها به درستی از API برگشت می‌کنند
    @Test
    fun `getProducts should return data correctly when API responds with data`() {
        val mockData = listOf(
            ProductDto(id = "1", name = "Product 1", referenceImageId = "image1"),
            ProductDto(id = "2", name = "Product 2", referenceImageId = "image2")
        )
        every { mockPager.load(any()) } returns mockData

        val result = repository.getProducts()

        assertTrue(result is List<ProductDto>)
        assertTrue(result.isNotEmpty())
        assertTrue(result.size == 2)
    }

    // تست محاسبه صحیح prevKey و nextKey
    @Test
    fun `getProducts should return correct prevKey and nextKey`() {
        val mockData = listOf(
            ProductDto(id = "1", name = "Product 1", referenceImageId = "image1"),
            ProductDto(id = "2", name = "Product 2", referenceImageId = "image2")
        )

        every { mockPager.load(any()) } returns mockData

        val result = repository.getProducts()

        assertNull(result.prevKey)
        assertEquals(1, result.nextKey)
    }

    // تست عدم تاثیر تغییرات در Pager بر رفتار repository
    @Test
    fun `getProducts should not affect Pager configuration`() {
        val originalPager = Pager(PagingConfig(pageSize = 20)) {
            mockk<ProductPagingSource>()
        }
        repository = RemoteProductListRepositoryImpl(originalPager)
        val result = repository.getProducts()
        assertEquals(20, result.config.pageSize)
    }
}

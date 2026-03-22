# CLAUDE.md — Project Rules

## Efficiency Rules (MANDATORY)

- **DO NOT explore the project.** The full structure map is below. Use it instead of ls/find/cat.
- **Batch writes:** Plan ALL changes to a file, then write the ENTIRE file in ONE Write command.
- **No re-reading:** After you write a file, do NOT read it back to "verify". Trust your output.
- **Silent execution:** Do not explain steps. Just execute.
- **Parallel downloads:** Batch all curl/wget image downloads into one shell script and run it once.
- **Write, don't edit:** Use Write (full file replacement), not Edit (partial patches).
- **No verification loops:** Do not grep for "TODO" or "placeholder" after writing.

## Content Rules

- ALL text in English — UI, strings.xml, comments, identifiers, logs, README
- No Lorem ipsum, no placeholders — real meaningful content only
- Stock images from Unsplash/Pexels only (not AI-generated)

## Project Structure (DO NOT explore — use this map)

Package path after scaffold: shop/youandmecreative/app

### Files to MODIFY (⚡):
- `data/repository/ProductRepository.kt` — empty products list → fill with real Product objects (id, name, description, price, imageRes, category)
- `data/model/ProductCategory.kt` — enum of product categories → add real categories
- `ui/composable/screen/home/HomeScreen.kt` — home layout → add hero carousel (HorizontalPager), category grid, product cards
- `ui/composable/screen/onboarding/OnboardingScreen.kt` — 3 slides → fill slide content (title, description, image)
- `ui/composable/screen/splash/SplashScreen.kt` — splash → add gradient + logo animation
- `ui/composable/screen/productdetails/ProductDetailsScreen.kt` — detail view → polish layout
- `ui/composable/screen/cart/CartScreen.kt` — cart UI → polish layout
- `ui/composable/screen/checkout/CheckoutScreen.kt` — checkout form → polish layout
- `ui/composable/screen/order/OrderScreen.kt` — order history → polish layout
- `ui/composable/screen/settings/SettingsScreen.kt` — settings → polish layout
- `ui/theme/Color.kt` — brand colors (already set in scaffold, verify only)
- `ui/theme/Theme.kt` — uses Skeleton naming → rename to match app, apply brand colors
- `ui/theme/Type.kt` — typography → customize if needed
- `YNMCRApp.kt` — Application class (already renamed)
- `res/values/strings.xml` — app_name and UI strings
- `res/drawable/` — download stock images here

### Files to NOT modify (infrastructure — already working):
- `MainActivity.kt` — entry point
- `data/dao/CartItemDao.kt`, `data/dao/OrderDao.kt` — Room DAOs
- `data/database/YNMCRDatabase.kt` — Room DB (already renamed)
- `data/database/converter/Converters.kt` — type converters
- `data/datastore/YNMCROnboardingPrefs.kt` — onboarding flag
- `data/entity/CartItemEntity.kt`, `data/entity/OrderEntity.kt` — Room entities
- `data/model/Product.kt` — data class (DO NOT change fields)
- `data/repository/CartRepository.kt` — cart logic
- `data/repository/YNMCROnboardingRepo.kt` — onboarding state
- `data/repository/OrderRepository.kt` — order logic
- `di/*` — Koin DI modules
- `ui/composable/approot/*` — AppRoot, AppBottomBar, AppTopBar
- `ui/composable/navigation/*` — NavHost, NavRoute
- `ui/composable/screen/checkout/CheckoutDialog.kt` — dialog
- `ui/composable/shared/*` — YNMCRContentWrapper, YNMCREmptyView
- `ui/state/*` — UI state classes
- `ui/viewmodel/*` — all ViewModels (they reference repository, no changes needed)

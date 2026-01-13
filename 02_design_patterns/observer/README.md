# Observer Pattern

## 1. Definition
> "Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically."

## 2. The Analogy: YouTube Subscription
* **Subject (The YouTuber):** Uploads a video. They don't know who their fans are, just that they have a list of them.
* **Observer (The Subscriber):** Wants to know when a video drops.
* **The Action:** Instead of the Subscriber refreshing the page every 5 seconds (Polling), the YouTuber pushes a notification when the event happens.

## 3. What We Refactored

### The "Bad" Way (Polling)
 The client repeatedly checks for updates.

    // Main.java
    while(true) {
        if (channel.hasNewVideo()) {
            System.out.println("New video!");
        }
        Thread.sleep(1000); // Wasting resources
    }

**Why this fails:**
* **Efficiency:** It wastes CPU cycles checking for updates that haven't happened.
* **Lag:** If the user checks every minute, they might be a minute late to the news.

### The "Good" Way (Push Notification)
The Subject notifies the Observer.

    // 1. The Subject (Channel)
    public void uploadVideo(String title) {
        notifySubscribers(title); // "Push" the data
    }

    // 2. The Observer (Subscriber)
    public void update(String title) {
        System.out.println("Got it: " + title);
    }

## 4. Real-World Examples
1.  **Chat Applications (WhatsApp/Slack):**
    * Subject: The Chat Room.
    * Observer: Your Phone.
    * When a message is sent, the server notifies all connected devices.
2.  **Stock Market Tickers:**
    * Subject: The Stock Price Data Feed.
    * Observer: The Trader's Dashboard.
    * When Apple stock goes up, the dashboard updates automatically.
3.  **JavaScript Event Listeners:**
    * `button.addEventListener('click', () => { ... })`
    * The Button is the Subject. Your function is the Observer.
4.  **MVC Architecture (Model-View-Controller):**
    * When the **Model** (Database data) changes, it notifies the **View** (UI) to redraw itself.

    
## 6. Architectural Trade-offs & Deep Dive

### Memory Management (The Lapsed Listener Problem)
One major risk in the Observer pattern is memory leaks.
* **The Issue:** When a Subject holds a strong reference to an Observer, the Garbage Collector cannot reclaim the Observer's memory, even if the Observer is no longer in use (e.g., a closed UI window).
* **The Fix:** Production systems often use `WeakReference` for observers or enforce strict `unsubscribe()` lifecycles to prevent "zombie" objects from accumulating in memory.

### Blocking vs. Asynchronous Notification
In a naive implementation, `notifySubscribers()` runs synchronously.
* **The Risk:** If one Observer performs a heavy operation (e.g., database write) inside its `update()` method, it blocks the Subject and all subsequent Observers.
* **Production Approach:** For high-throughput systems, notifications should be offloaded to an Event Bus (e.g., Kafka, RabbitMQ) or run asynchronously using `CompletableFuture` / `ExecutorService` to ensure the Subject remains responsive.

### Data Distribution: Push vs. Pull Models
* **Push Model (Implemented):** The Subject sends the data payload (`videoTitle`) directly in the update.
    * *Pro:* Reduces latency; Observers get data immediately.
    * *Con:* Can be inefficient if the payload is large and some Observers only care about the "event," not the data.
* **Pull Model:** The Subject sends a generic "State Changed" event, and Observers call `subject.getData()` to fetch what they need.
    * *Pro:* More flexible; Observers fetch only what they need.
    * *Con:* Two-step communication (Notification + Callback) adds complexity and coupling.
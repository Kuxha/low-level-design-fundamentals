# Open/Closed Principle (OCP)

## Definition
> "Software entities (classes, modules, functions) should be **OPEN for extension**, but **CLOSED for modification**."

## The Analogy: Video Game Console
Think of a **Nintendo Switch**:
* **The Console (Closed):** The hardware is sealed. You don't unscrew the back and solder new wires every time you want to play a new game.
* **The Game Cartridge (Open):** To play a new game (Zelda, Mario), you just plug in a new cartridge.
* **The Result:** The console stays the same (Closed), but the gameplay can be infinitely extended (Open).

## What We Refactored

### The "Bad" Way (Modification)
We started with a `NotificationSender` that checked every type manually:

    if (type.equals("email")) { ... }
    else if (type.equals("sms")) { ... }

**Why it fails:** If we want to add "WhatsApp", we have to edit this file. If we break a bracket or variable here, we break Email sending too!

### The "Good" Way (Extension)
We created a common `Notification` interface.
1.  **Interface:** Defined a contract `send()`.
2.  **Polymorphism:** `EmailNotification` and `SMSNotification` implement their own logic.
3.  **The Sender:** It blindly calls `notification.send()`. It doesn't know (or care) if it's sending an Email or a WhatsApp message.

**Result:** To add WhatsApp, we just create `WhatsAppNotification.java`. We **never** touch the `NotificationSender` again.

## Real-World Practical Use Cases

### 1. Payment Processing (The Classic)
* **Scenario:** An e-commerce site accepts Credit Cards.
* **Extension:** Business wants to accept PayPal and Bitcoin.
* **OCP:** Create `PayPalProcessor` and `BitcoinProcessor` implementing a `PaymentStrategy` interface. The checkout code remains untouched.

### 2. Logging Frameworks
* **Scenario:** Your app currently prints logs to the Console (`System.out`).
* **Extension:** You need to save logs to a File or send them to the Cloud (Datadog/Splunk).
* **OCP:** Create `FileLogger` and `CloudLogger`. The main application just calls `logger.log("Error")` without caring where it goes.

### 3. File Upload Service
* **Scenario:** Users upload profile pictures to a local server folder.
* **Extension:** You scale up and need to save files to Amazon S3 or Google Cloud Storage.
* **OCP:** Create `S3StorageService` and `GCPStorageService` implementing a `StorageInterface`. The upload controller doesn't need to change.
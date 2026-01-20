# Use Cases

## 1. Create Form
**Primary Actor:** Form Creator  
**Goal:** Design and publish a new form for data collection.  
**Preconditions:** Form creator is authenticated via OAuth.  
**Main Flow:**  
1. Form creator logs in using OAuth.  
2. Form creator selects 'Create New Form'.  
3. Form creator adds fields (text, number, date, choice).  
4. Form creator configures field properties (required, optional, etc.).  
5. Form creator saves and publishes the form.  
**Postconditions:** New form is available for fillers.

## 2. Edit Form
**Primary Actor:** Form Creator  
**Goal:** Modify an existing form.  
**Preconditions:** Form creator is authenticated and owns the form.  
**Main Flow:**  
1. Form creator logs in using OAuth.  
2. Form creator selects a form to edit.  
3. Form creator updates fields or properties.  
4. Form creator saves changes.  
**Postconditions:** Form is updated for fillers.

## 3. Delete Form
**Primary Actor:** Form Creator  
**Goal:** Remove a form from the system.  
**Preconditions:** Form creator is authenticated and owns the form.  
**Main Flow:**  
1. Form creator logs in using OAuth.  
2. Form creator selects a form to delete.  
3. Form creator confirms deletion.  
**Postconditions:** Form is removed and no longer available.

## 4. Fill Form
**Primary Actor:** Form Filler  
**Goal:** Submit responses to a published form.  
**Preconditions:** Form filler is authenticated via OAuth; form is published.  
**Main Flow:**  
1. Form filler logs in using OAuth.  
2. Form filler selects a form to fill.  
3. Form filler enters responses in fields.  
4. Form filler submits the form.  
**Postconditions:** Responses are stored securely.

## 5. View Responses
**Primary Actor:** Form Creator  
**Goal:** Review submitted responses for a form.  
**Preconditions:** Form creator is authenticated and owns the form.  
**Main Flow:**  
1. Form creator logs in using OAuth.  
2. Form creator selects a form.  
3. Form creator views responses in the application.  
4. Form creator exports responses if needed.  
**Postconditions:** Responses are available for analysis/export.

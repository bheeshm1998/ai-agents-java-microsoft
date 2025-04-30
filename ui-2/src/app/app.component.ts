import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { marked } from 'marked';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';
import { catchError, finalize } from 'rxjs/operators';
import { of } from 'rxjs';
import html2pdf from 'html2pdf.js';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  textForm!: FormGroup;
  markdownOutput: string = '';
  htmlOutput: string = `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sample HTML with Styles</title>
    <style>
        /* General Styles */
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #333;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        
        /* Typography */
        h1 {
            color: #2c3e50;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
        }
        
        h2 {
            color: #2980b9;
        }
        
        /* Navigation */
        nav {
            background-color: #2c3e50;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        
        nav ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            display: flex;
            gap: 20px;
        }
        
        nav a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }
        
        nav a:hover {
            color: #3498db;
        }
        
        /* Form Elements */
        .form-group {
            margin-bottom: 15px;
        }
        
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        
        input[type="text"],
        input[type="email"],
        input[type="password"],
        select,
        textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        
        button {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        
        button:hover {
            background-color: #2980b9;
        }
        
        /* Table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        
        th {
            background-color: #3498db;
            color: white;
        }
        
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        
        /* Images and Media */
        .image-container {
            text-align: center;
            margin: 20px 0;
        }
        
        img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        
        /* Cards */
        .card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            padding: 20px;
            margin-bottom: 20px;
        }
        
        /* Footer */
        footer {
            text-align: center;
            padding: 20px;
            margin-top: 40px;
            background-color: #2c3e50;
            color: white;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Sample HTML Page</h1>
        <nav>
            <ul>
                <li><a href="#home">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#services">Services</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="card">
            <h2>About Us</h2>
            <p>This is a sample paragraph containing some <strong>bold text</strong> and <em>italicized text</em>. Here's a <a href="#">link</a> to nowhere.</p>
            
            <div class="image-container">
                <img src="https://images.unsplash.com/photo-1743360543515-d3b506e6d3c2?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Placeholder image">
                <p><small>Sample caption for the image</small></p>
            </div>
        </section>

        <section class="card">
            <h2>Our Services</h2>
            <ul>
                <li>Web Development</li>
                <li>Graphic Design</li>
                <li>Digital Marketing</li>
                <li>SEO Optimization</li>
            </ul>
            
            <ol>
                <li>First step</li>
                <li>Second step</li>
                <li>Third step</li>
            </ol>
        </section>
<img src="https://images.unsplash.com/photo-1743360543515-d3b506e6d3c2?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Placeholder image">
        <section class="card">
            <h2>Pricing</h2>
            <table>
                <thead>
                    <tr>
                        <th>Plan</th>
                        <th>Features</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Basic</td>
                        <td>Standard features</td>
                        <td>$9.99/month</td>
                    </tr>
                    <tr>
                        <td>Pro</td>
                        <td>Advanced features</td>
                        <td>$19.99/month</td>
                    </tr>
                    <tr>
                        <td>Enterprise</td>
                        <td>All features + support</td>
                        <td>$49.99/month</td>
                    </tr>
                </tbody>
            </table>
        </section>

        <section class="card">
            <h2>Contact Us</h2>
            <form>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" placeholder="Your name">
                </div>
                
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" placeholder="Your email">
                </div>
                
                <div class="form-group">
                    <label for="service">Service:</label>
                    <select id="service" name="service">
                        <option value="">Select a service</option>
                        <option value="web">Web Development</option>
                        <option value="design">Graphic Design</option>
                        <option value="marketing">Digital Marketing</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="message">Message:</label>
                    <textarea id="message" name="message" rows="5" placeholder="Your message"></textarea>
                </div>
                
                <div class="form-group">
                    <label>
                        <input type="checkbox" name="subscribe"> Subscribe to newsletter
                    </label>
                </div>
                
                <button type="submit">Send Message</button>
            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2023 Sample Company. All rights reserved.</p>
    </footer>
</body>
</html>`;
  isLoading: boolean = false;
  hasGenerated: boolean = false;
  apiError: string | null = null;

  baseURL: string = 'http://localhost:8080/api/v1/story'; // Replace with your actual API endpoint
//  baseURL: string = "https://azure-ai-backend-aqebbxbrexeeb4a6.centralindia-01.azurewebsites.net/api/v1/story"
  
@ViewChild('markdownContainer') markdownContainer!: ElementRef;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.textForm = this.fb.group({
      inputText: ['', [Validators.required, Validators.maxLength(5000)]],
      numberOfScenes: [1, [Validators.required, Validators.min(1), Validators.max(3)]],
      imageIllustrationStyle: ['detail outline sketch', Validators.required]
    });

    // const element = this.markdownContainer.nativeElement;

    // // Ensure the markdownContainer has the rendered HTML content
    // element.innerHTML = this.htmlOutput;
  }

  ngAfterViewInit() {
    const element = this.markdownContainer.nativeElement;
    // Ensure the markdownContainer has the rendered HTML content
    element.innerHTML = this.htmlOutput;
  }

  generateMarkdown() {
    this.isLoading = true;
    this.apiError = null;
  
    // Extract form values
    const inputText = this.textForm.get('inputText')?.value;
    const numberOfScenes = this.textForm.get('numberOfScenes')?.value;
    const imageIllustrationStyle = this.textForm.get('imageIllustrationStyle')?.value;
  
    // Prepare the request payload
    const payload = {
      story: inputText,
      numberOfEvents: numberOfScenes,
      artStyle: imageIllustrationStyle
    };
  
    // Call the backend API
    this.http.post<string>(`${this.baseURL}/illustrate`, payload).subscribe(
      (response: any) => {

        if (response.markdown) {
          console.log('Generated Markdown:', response.markdown);
          this.markdownOutput = response.markdown; // Directly assign the response as markdown
          this.htmlOutput = marked(this.markdownOutput); // Convert markdown to HTML
          this.hasGenerated = true;

          const element = this.markdownContainer.nativeElement;

          // Ensure the markdownContainer has the rendered HTML content
          element.innerHTML = this.htmlOutput;

        }
      },
      (error) => {
        console.error('Error generating markdown:', error);
        this.apiError = 'An error occurred while generating the markdown. Please try again.';
      },
      () => {
        this.isLoading = false; // Ensure loading state is reset
      }
    );
  }

  // Mock API call - replace with actual API in production
  mockApiCall(text: string) {
    // This is a simple mock that converts plain text to markdown
    // In a real application, you would call your backend API
    const mockMarkdown = this.convertToMockMarkdown(text);
    return of({ markdown: mockMarkdown });
  }

  // Simple function to convert text to mock markdown for demonstration
  private convertToMockMarkdown(text: string): string {
    if (!text) return '';
    
    // Split by paragraphs
    const paragraphs = text.split(/\n\s*\n/);
    
    // Process each paragraph
    return paragraphs.map((para, index) => {
      para = para.trim();
      
      // First paragraph as heading
      if (index === 0) {
        return `# ${para}`;
      }
      
      // Random formatting for demonstration
      if (index % 3 === 0) {
        return `## ${para}`;
      } else if (index % 5 === 0) {
        return `- ${para.replace(/\n/g, '\n- ')}`;
      } else {
        return para;
      }
    }).join('\n\n');
  }

  downloadAsMarkdown() {
    if (!this.markdownOutput) return;
    
    const blob = new Blob([this.markdownOutput], { type: 'text/markdown' });
    this.downloadBlob(blob, 'document.md');
  }

  downloadAsPDF() {
    if (!this.markdownContainer || !this.htmlOutput) {
        console.error('No content available to generate PDF.');
        return;
    }

    const element = this.markdownContainer.nativeElement;

    // Ensure the markdownContainer has the rendered HTML content
    element.innerHTML = this.htmlOutput;

    html2canvas(element).then(canvas => {
        const imgData = canvas.toDataURL('image/png');
        const pdf = new jsPDF({
            orientation: 'portrait',
            unit: 'mm',
            format: 'a4'
        });

        const imgProps = pdf.getImageProperties(imgData);
        const pdfWidth = pdf.internal.pageSize.getWidth();
        const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;

        pdf.addImage(imgData, 'PNG', 0, 0, pdfWidth, pdfHeight);
        pdf.save('document.pdf');
    }).catch(error => {
        console.error('Error generating PDF:', error);
    });
}

downloadAsPDF2() {
  console.log('Generating PDF2...');
  if (!this.markdownContainer || !this.htmlOutput) {
    console.error('No content available to generate PDF.');
    return;
  }

  // Get the content element
  const element = this.markdownContainer.nativeElement;
  
  // Make sure it has the rendered HTML content
  element.innerHTML = this.htmlOutput;
  
  // Set some styling options for better PDF rendering
  const opt = {
    margin: [10, 10, 10, 10],
    filename: 'documentlodu.pdf',
    image: { type: 'jpeg', quality: 0.98 },
    html2canvas: { 
      scale: 2, // Higher scale for better quality
      useCORS: true,
      logging: false,
      letterRendering: true
    },
    jsPDF: { 
      unit: 'mm', 
      format: 'a4', 
      orientation: 'portrait' 
    }
  };
  
  // Use html2pdf library which handles complex layouts better
  html2pdf().from(element).set(opt).save();
}

  private downloadBlob(blob: Blob, filename: string) {
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = filename;
    link.click();
    window.URL.revokeObjectURL(url);
  }

  clearText() {
    this.textForm.get('inputText')?.setValue('');
    this.markdownOutput = '';
    this.htmlOutput = '';
    this.hasGenerated = false;
    this.apiError = null;
  }

  testAPI() {

    this.apiError = null;

    this.http.get(`${this.baseURL}/test`).pipe(
      catchError(error => {
        this.apiError = 'An error occurred while testing the API. Please try again.';
        return of(null);
      }),
      finalize(() => {
        this.isLoading = false;
      })
    ).subscribe(response => {
      if (response) {
        console.log('API response:', response);
      }
    });
  }
}
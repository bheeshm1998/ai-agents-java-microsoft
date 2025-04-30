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
  htmlOutput: string = ``;
  isLoading: boolean = false;
  hasGenerated: boolean = false;
  apiError: string | null = null;

  // baseURL: string = 'http://localhost:8080/api/v1/story'; // Replace with your actual API endpoint
 baseURL: string = "https://azure-ai-backend-aqebbxbrexeeb4a6.centralindia-01.azurewebsites.net/api/v1/story"
  
@ViewChild('markdownContainer') markdownContainer!: ElementRef;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.textForm = this.fb.group({
      inputText: ['', [Validators.required, Validators.maxLength(5000)]],
      numberOfScenes: [1, [Validators.required, Validators.min(1), Validators.max(3)]],
      imageIllustrationStyle: ['pixar style', Validators.required]
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
        this.apiError = 'An error occurred. Please try again.';
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

  const element = this.markdownContainer.nativeElement;
  element.innerHTML = this.htmlOutput;

  const images = element.querySelectorAll('img');
  const imageLoadPromises = Array.from(images).map((img: any) => {
    return new Promise<void>((resolve) => {
      if (img.complete) {
        resolve();
      } else {
        img.onload = () => resolve();
        img.onerror = () => resolve(); // Prevent hang on failed images
      }
    });
  });

  Promise.all(imageLoadPromises).then(() => {
    const opt = {
      margin: [10, 10, 10, 10],
      filename: 'documentlodu.pdf',
      image: { type: 'jpeg', quality: 0.98 },
      html2canvas: { 
        scale: 2,
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

    html2pdf().from(element).set(opt).save();
  });
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
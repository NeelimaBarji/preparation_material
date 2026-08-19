---
project: Pediatric Dosing Fidelity Systematic Review
status: pre-registration draft
version: 0.1
date: 2026-08-19
author: Neelima Barji
---

# Pediatric Dosing Fidelity — Systematic Review Concept Brief

> Working file. Everything needed to register the protocol and start screening.
> Companion documents: `pediatric_dosing_SR_protocol.md` (full method),
> `q1_nearest_neighbour_ideas.md` (follow-on studies).

---

## 1. Title

**Primary (recommended):**

> **Numeric and Dosing Fidelity in Large Language Model–Generated Pediatric Clinical Documentation and Communication: A Systematic Review**

Names population, technology, outcome class, and design — what PRISMA asks for and what makes it findable. "Fidelity" is deliberate: broader than "accuracy," it covers transcription and translation errors alongside calculation.

**Alternatives, by scope outcome:**

| If… | Use |
|---|---|
| Search yields mostly dose-calculation studies, little documentation context | *Accuracy and Safety of Large Language Models in Pediatric Medication Dosing: A Systematic Review* |
| Synthesis is dominated by error taxonomy rather than error rates | *Error Types and Reporting Practices in Large Language Model Pediatric Dosing Evaluations: A Systematic Review* |
| Fewer than ~15 studies included (pre-specified fallback) | *…: A Systematic Scoping Review* — the primary title converts with a two-word edit |

Avoid a punchy colon-phrase title here. Save "Beyond Arithmetic" / "Correct Arithmetic, Unsafe Dose" for the benchmark paper, where it's earned.

---

## 2. Keywords

**Manuscript keywords (6–8, journal submission):**

`large language models` · `pediatrics` · `medication dosing` · `patient safety` · `clinical documentation` · `medication errors` · `artificial intelligence` · `systematic review`

**MeSH terms (for indexing and the search):**

- `Pediatrics` · `Child` · `Infant` · `Infant, Newborn` · `Adolescent`
- `Artificial Intelligence` · `Natural Language Processing` · `Deep Learning`
- `Drug Dosage Calculations` · `Medication Errors` · `Drug Prescriptions` · `Patient Safety`
- `Electronic Health Records` · `Documentation` · `Health Communication`

**Free-text / non-MeSH (the field moves faster than MeSH):**

`GPT` · `ChatGPT` · `generative AI` · `foundation model` · `ambient AI scribe` · `weight-based dosing` · `mg/kg` · `dose ceiling` · `hallucination` · `numeric reasoning` · `tool augmentation`

> **Note:** MeSH has no term for "large language model" as of this writing — free-text `[tiab]` terms are load-bearing, not optional. A MeSH-only search will miss most of the relevant literature.

---

## 3. Background in five sentences

Pediatric dosing is weight-normalized, so a dose depends on a number that changes rapidly with age and is often stale in the chart. Weight-based calculation is overridden by an adult ceiling above certain weights, and neonatal dosing depends on age concepts (gestational, postmenstrual, corrected) with no adult analogue. LLMs are now generating, transcribing, and translating the clinical text that carries these numbers — ambient scribes, portal replies, discharge instructions. Existing evaluations of LLM clinical calculation and existing pediatric LLM reviews do not isolate numeric and dosing fidelity in the pediatric population as an outcome class. No review has synthesized what is known.

---

## 4. Gaps this review addresses

**G1 — No pediatric-specific synthesis of numeric fidelity.** Two 2026 reviews cover pediatric LLM use broadly (JAMA Netw Open scoping; J Pediatr Surg systematic), neither isolates dosing/numeric outcomes.

**G2 — Setup errors vs. execution errors are conflated.** Tool-use studies show LLMs can invoke calculators; a 2026 critique argues existing benchmarks conflate arithmetic *execution* with problem *setup* (which formula, which weight, which unit, whether a ceiling applies). In pediatrics, setup is where the harm lives. Nobody has decomposed the two.

**G3 — Error direction and magnitude are rarely reported.** Accuracy percentages treat a 20% underdose of an antipyretic and a 20% overdose of an opioid identically. Whether primary studies report direction and fold-error at all is an open empirical question — and likely a headline finding.

**G4 — Pediatric-unique failure modes have no benchmark.** Dose ceilings in adolescents, temporally stale weights, gestational-vs-chronological age confusion, and formulation/volume realizability are invisible to every existing evaluation because adult medicine has no analogue.

**G5 — No risk-of-bias domain covers pediatric evaluation validity.** No existing tool asks whether a test set spans the weight range where ceilings bind, or whether neonatal age concepts were disambiguated.

**G6 — Reference standards are heterogeneous and under-reported.** What counts as a "correct" pediatric dose varies by label, formulary, and institution; how primary studies establish ground truth has not been mapped.

---

## 5. Review questions

**Primary:** What is known about the accuracy and safety of numeric and dosing content in LLM-generated or LLM-transcribed pediatric clinical documentation and patient/caregiver communication?

**Secondary:**

1. What is the distribution of error types (arithmetic execution · unit conversion · weight selection · stale weight · dose-ceiling omission · age-concept confusion · formulation realizability · transcription)?
2. Do studies report error **direction** and **magnitude**, or only undifferentiated accuracy?
3. Does tool augmentation / retrieval / a verification layer measurably reduce pediatric numeric error — and which error classes survive it?
4. What reference standards are used, and how much clinician involvement grades correctness?
5. How complete is reporting against TRIPOD-LLM and CHART?
6. Which age strata (neonate · infant · child · adolescent) are represented, and which are absent?

---

## 6. Seed set for search validation

**Use this before committing to a search string.** If your Boolean does not retrieve these, it is not ready. Document the validation — reviewers value it and almost nobody reports it.

### 6a. Must-retrieve (directly on topic)

| Paper | Venue | Why it's a seed |
|---|---|---|
| [Can large language models assist with pediatric dosing accuracy?](https://www.nature.com/articles/s41390-025-03980-8) ([PMC](https://pmc.ncbi.nlm.nih.gov/articles/PMC12602315/)) | Pediatric Research, 2025 | The closest existing primary study |
| [LLMs and clinical calculations: to err is human and machines are not exempt](https://www.nature.com/articles/s41390-025-04166-y) | Pediatric Research, 2025 | Companion commentary |
| [Evaluating AI LLMs in Detecting Pediatric Medication Errors Across Languages](https://doi.org/10.3390/jcm15010162) | J Clin Med, 2026 | Pediatric + medication error + cross-language |
| [LLM agents can use tools to perform clinical calculations](https://www.nature.com/articles/s41746-025-01475-8) | npj Digital Medicine, 2025 | The tool-augmentation result |
| [MedCalc-Bench: Evaluating LLMs for Medical Calculations](https://proceedings.neurips.cc/paper_files/paper/2024/hash/99e81750f3fdfcaf9613db2dbf4bd623-Abstract-Datasets_and_Benchmarks_Track.html) ([GitHub](https://github.com/ncbi-nlp/MedCalc-Bench)) | NeurIPS 2024 | Foundational benchmark |
| [MedCalc-Bench Doesn't Measure What You Think](https://arxiv.org/html/2603.02222v1) | preprint, 2026 | The setup-vs-execution critique — central to G2 |
| [MedCalc-Eval and MedCalc-Env](https://arxiv.org/html/2510.27267) | preprint, 2025 | Benchmark successor |
| [LLMs for preventing medication direction errors in online pharmacies](https://www.nature.com/articles/s41591-024-02933-8) | Nature Medicine, 2024 | Medication-direction fidelity |
| [RxEval: A Prescription-Level Benchmark](https://arxiv.org/pdf/2605.14543) | preprint, 2026 | Prescription-level evaluation |
| [Can I Take Another Dose? LLM Decision-Making Under Temporal Uncertainty in OTC Dosing QA](https://arxiv.org/html/2606.04262) | preprint, 2026 | Temporal reasoning in dosing |

### 6b. Context / likely-included (documentation & communication channel)

- [Ambient AI Scribes in Pediatric Primary Care: A Mixed Methods Study](https://pubmed.ncbi.nlm.nih.gov/40456513/) — PubMed, 2025
- [Effect of a generative AI digital scribe on pediatric provider documentation time, cognitive burden, and burnout](https://pmc.ncbi.nlm.nih.gov/articles/PMC12225174/) — PMC, 2025
- [Barriers and opportunities of scaling ambient AI scribes](https://www.nature.com/articles/s41746-026-02554-0) — npj Digital Medicine, 2026
- [Effectiveness of LLMs in preoperative and discharge education: a systematic review](https://www.nature.com/articles/s41746-025-02302-w) — npj Digital Medicine, 2025
- [LLM as clinical decision support system augments medication safety in 16 clinical specialties](https://pmc.ncbi.nlm.nih.gov/articles/PMC12629785/) — 2025
- [Physician- and LLM-Generated Hospital Discharge Summaries](https://pubmed.ncbi.nlm.nih.gov/40323616/) — 2025
- [Feasibility of an LLM Chatbot to Support Parental Understanding in the PICU](https://journals.lww.com/ccejournal/fulltext/10.1097/cce.0000000000001378~feasibility-of-a-large-language-model-chatbot-to-support) — 2026
- [Large language models provide unsafe answers to patient-posed medical questions](https://www.nature.com/articles/s41746-026-02428-5) — npj Digital Medicine, 2026

### 6c. Existing reviews — do NOT duplicate; cite as the gap

- [Large Language Models Using Clinical Text in Pediatrics: A Scoping Review](https://pmc.ncbi.nlm.nih.gov/articles/PMC13019234/) — JAMA Netw Open, 2026 ([editorial](https://jamanetwork.com/journals/jamanetworkopen/articlepdf/2846946/hron_2026_ic_260040_1773848838.75222.pdf))
- [LLMs for electronic health records in pediatric and surgical care: A systematic review](https://www.jpedsurg.org/article/S0022-3468(26)00039-4/fulltext) — J Pediatr Surg, 2026
- [Application of AI tools and clinical documentation burden: systematic review and meta-analysis](https://pmc.ncbi.nlm.nih.gov/articles/PMC12836966/) — 2026

### 6d. Clinical grounding (motivation, not included studies)

- [Tenfold medication errors: 5 years at a university-affiliated pediatric hospital](https://pubmed.ncbi.nlm.nih.gov/22473367/) — Pediatrics, 2012
- [Methodological Challenges in Describing Medication Dosing Errors in Children](https://www.ncbi.nlm.nih.gov/books/NBK20505/) — AHRQ
- [Health Literacy–Informed Communication to Reduce Discharge Medication Errors in Hospitalized Children: RCT](https://jamanetwork.com/journals/jamanetworkopen/fullarticle/2813929) — JAMA Netw Open
- [Machine learning risk prediction for medication administration errors in NICUs](https://pmc.ncbi.nlm.nih.gov/articles/PMC11489987/) — 2024

---

## 7. Open datasets and resources

**For the review itself you need no patient data.** This section is for the follow-on benchmark work — start the access paperwork early, because approvals take weeks and the review gives you the waiting time for free.

### 7a. Freely available, no application

| Resource | What it gives you | Notes |
|---|---|---|
| [openFDA Drug Label API](https://open.fda.gov/apis/drug/label/) ([examples](https://open.fda.gov/apis/drug/label/example-api-queries), [fields](https://open.fda.gov/apis/openfda-fields/)) | FDA structured product labels — dosage & administration, pediatric use, overdosage | **Your primary ground-truth source for dose ceilings.** Free, no key needed for moderate use, citable, versioned |
| DailyMed (NLM) | Same SPL content, browsable | Cross-check for openFDA gaps |
| [Synthea](https://synthetichealth.github.io/synthea/) ([GitHub](https://github.com/synthetichealth/synthea), [AWS OMOP mirror](https://registry.opendata.aws/synthea-omop/)) | Synthetic longitudinal patient records incl. pediatric growth trajectories and medications | **No IRB, no DUA, fully open.** Best fit for the stale-weight study — it generates the longitudinal weights you need |
| [MedCalc-Bench](https://github.com/ncbi-nlp/MedCalc-Bench) | Existing medical calculation benchmark + code | Reuse the harness; note it is not pediatric-specific |
| CDC / WHO growth charts | Reference percentiles, LMS parameters | Needed to make synthetic weight-for-age plausible |
| RxNorm / RxNav (NLM) | Drug normalization, ingredient mapping, available strengths | Essential for the formulation-realizability study |

### 7b. Credentialed access — apply early

| Resource | Coverage | Access reality |
|---|---|---|
| [PIC — Paediatric Intensive Care Database v1.1.0](https://physionet.org/content/picdb/1.1.0/) ([paper](https://www.nature.com/articles/s41597-020-0355-4)) | **All patients <18y**, 5 ICUs, 2010–2018, single centre (China), bilingual. Vitals, medications, labs, fluid balance, diagnoses | PhysioNet credentialed: CITI "Data or Specimens Only Research" training + signed DUA; ~3 working days after credentialing. **The main genuinely pediatric EHR dataset that is openly obtainable** |
| [MIMIC-IV v3.1](https://physionet.org/content/mimiciv/3.1/) | **Neonates explicitly removed**; a separate NICU release is planned | Same PhysioNet credentialing. ⚠️ **Do not assume MIMIC gives you pediatric data — it does not.** Note this constraint in any grant or protocol |
| MIMIC-CXR | Adult chest imaging + reports | Relevant to your plain-language anchor review, not to dosing |

> **Practical sequencing:** complete PhysioNet credentialing (CITI course, a few hours) *now*, during the review. It expires and renews on a cycle, so having it in hand removes a multi-week blocker from every future study.

### 7c. Tooling for the review

| Need | Tool |
|---|---|
| Screening (blinded, dual, κ) | **Rayyan** (free) |
| Reference management | Zotero + Better BibTeX |
| Deduplication | Zotero, or the Deduklick/ASySD approach |
| Flow diagram | PRISMA 2020 flow generator |
| Registration | PROSPERO, or **OSF Registries** as fallback (§9) |
| Preprint | medRxiv (clinical) or arXiv (methods) |

---

## 8. Frameworks and checklists to apply

| Purpose | Instrument |
|---|---|
| Review reporting | **PRISMA 2020**; **PRISMA-P** (protocol); **PRISMA-S** (search); **PRISMA-ScR** if the scoping fallback triggers |
| Risk of bias — LLM Q&A / benchmark studies (the bulk) | [**AQAT:RoB**](https://www.jmir.org/2026/1/e87057) — Alberta Quality Assessment Tool, purpose-built for medical LLM Q&A studies |
| Risk of bias — diagnostic-accuracy framed | QUADAS-2 + [QUADAS-AI](https://pmc.ncbi.nlm.nih.gov/articles/PMC11447435/) |
| Risk of bias — prediction models | [PROBAST+AI](https://www.cochrane.org/sites/default/files/uploads/PDFs/Methods%20reports/2025/Spotlight%20On_Prognosis.pdf) |
| Risk of bias — RCT / non-randomized | RoB 2 · ROBINS-I |
| Reporting completeness of included studies | [**TRIPOD-LLM**](https://www.nature.com/articles/s41591-024-03425-5) · [**CHART**](https://pmc.ncbi.nlm.nih.gov/articles/PMC12320030/) (chatbot studies) |
| Certainty of evidence | GRADE (applied descriptively — justify the deviation) |
| Critical-appraisal tool selection | [Critical Appraisal Tools for Evaluating AI in Clinical Studies: Scoping Review](https://www.jmir.org/2025/1/e77110) |
| Novel contribution | **Your own pediatric bias domain** — case-mix spans ceiling-binding weights? reference standard pediatrically valid? age strata reported? |

---

## 9. Registration

- **First choice:** PROSPERO. Frame the outcome as **patient safety / medication error**, not "model accuracy" — PROSPERO requires a health-related outcome and may reject a pure in-silico benchmark review as out of scope.
- **Fallback:** OSF Registries (free, any design, timestamped) or INPLASY. Satisfies most journals' prospective-registration requirements.
- **Register before screening begins.** Retrospective registration is detectable.
- **Pre-specify the scoping switch:** *"If fewer than 15 studies meet inclusion criteria, the review will be reported as a systematic scoping review following PRISMA-ScR, with identical search and extraction methods."*

---

## 10. Target venues

| Venue | Quartile (SJR) | Fit |
|---|---|---|
| npj Digital Medicine | **Q1** (SJR 4.215) | If the synthesis yields a strong safety signal |
| JAMIA | **Q1** (SJR 1.870) | Best fit for the methodological framing |
| J Medical Internet Research | **Q1** (SJR 2.109) | Solid alternative |
| JAMIA Open | **Q1** (SJR 1.085, 2024) | Open access, receptive to reviews and benchmarks |
| Journal of Biomedical Informatics | Q1 | Methods-heavy audience |
| JMIR Formative Research | Q2 (SJR 0.763) | Faster, lower bar — but Q2 |

Check APCs and waiver policies before committing; the open-access options charge.

---

## 11. Pre-screening checklist

- [ ] Complete PhysioNet credentialing (CITI training) — unblocks all follow-on work
- [ ] Assemble the §6a seed set in Zotero
- [ ] Draft search strings for all 7 databases
- [ ] **Validate** strings against the seed set; iterate until all are retrieved
- [ ] Record final strings + dates + yields
- [ ] Recruit a second screener (pediatric pharmacist ideal — also solves clinical adjudication)
- [ ] Draft protocol from `pediatric_dosing_SR_protocol.md`
- [ ] Register (PROSPERO → OSF fallback)
- [ ] Set up Rayyan project
- [ ] Pilot extraction form on 5 studies
- [ ] **Only then** begin screening

---

## 12. Known risks

| Risk | Mitigation |
|---|---|
| Too few includable studies | Pre-specified scoping-review switch (§9) |
| PROSPERO rejects as out of scope | Safety framing + OSF fallback ready |
| Solo screening weakens credibility | Recruit a second screener; else 20% dual-screen with κ, disclosed |
| Search goes stale before submission | Re-run before submission; report the update date |
| Clinical significance calls beyond your expertise | Extract author-reported classifications only; do not impose your own without a clinician co-author |
| Heterogeneity makes pooling meaningless | Pre-commit in the protocol to narrative synthesis; do not pool across differing tasks and reference standards |



---


## Recommended Simple Titles:

### Dosing Accuracy and Safety of Large Language Models in Pediatrics: A Systematic Review

Twelve words, no jargon, and every word is searchable. It drops "numeric fidelity" (which reads as invented terminology) and "documentation and communication" (which was doing scope work the abstract can do instead).

Other options, shorter to longer:

### Large Language Models and Pediatric Dosing: A Systematic Review

Nine words. Cleanest, but "and" is vague about what you're actually measuring.

### Medication Dosing Errors in Large Language Model–Generated Pediatric Clinical Text: A Systematic Review

Keeps the text-generation context. "Errors" is punchier than "accuracy" but slightly presupposes you'll find them — defensible for a review about error characterization, though a cautious reviewer may push back.

### Accuracy and Safety of Large Language Models in Pediatric Medication Dosing: A Systematic Review

Fourteen words. Closest to conventional clinical phrasing if you want to play it safe.

One reason the recommended version is also the safer choice: dropping "clinical documentation and communication" means the title survives either search outcome. If your fourth Boolean block cuts the yield too far and you widen to pediatric dosing generally, the title still fits — no re-registration needed.

Two small conventions: spell out "Large Language Models" rather than "LLMs" (better indexing, and some journals require expansion on first use anyway), and keep "A Systematic Review" — PRISMA asks for the design in the title.
